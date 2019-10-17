package com.andrew.commands.impl;

import com.andrew.commands.Command;
import com.andrew.service.impl.AttachmentServiceImpl;
import com.andrew.validation.HasNumberValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class DownloadAttachment implements Command {
    private Logger logger = Logger.getLogger(DownloadAttachment.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            if (HasNumberValidation.isNumber(request.getParameter("attachment_id"))) {
                Integer id = Integer.parseInt(request.getParameter("attachment_id"));
                HashMap<Integer, String> pair = new AttachmentServiceImpl().getContactIdAndFileName(id);
                Integer contactId = null;
                String fileName = null;
                for (Map.Entry<Integer, String> entry : pair.entrySet()) {
                    contactId = entry.getKey();
                    fileName = entry.getValue();
                }
                String file = id + "." + fileName.split(File.separator + ".")[0];
                PrintWriter out = response.getWriter();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                String fullPath = new AttachmentServiceImpl().getPath(contactId, file);

                FileInputStream stream = new FileInputStream(fullPath);
                int i;
                while ((i = stream.read()) != -1) {
                    out.write(i);
                }
                stream.close();
                response.setStatus(HttpServletResponse.SC_OK);

            } else {
                response.getWriter().write(new ObjectMapper().writeValueAsString("Attachments can't be downloaded"));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error(e);
        }


    }
}
