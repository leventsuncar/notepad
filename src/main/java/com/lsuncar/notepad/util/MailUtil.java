package com.lsuncar.notepad.util;

import com.lsuncar.notepad.core.util.ResourceBundleUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailUtil {

    @Value( "${frontend.configuration.host}" )
    private String frontendConfigurationHost;

    public String createMailText(String lang, Long userId) {

        String resetPassword = ResourceBundleUtil.getLocaleText("resetPassword", lang); // "Reset password";
        String resetPasswordHref = frontendConfigurationHost + "/respwd/" + userId;
        String contactSupportTeam = ResourceBundleUtil.getLocaleText("contactSupportTeam", lang);// "If you have any questions, feel free to contact our support team.";
        String ifButtonNotWork = ResourceBundleUtil.getLocaleText("ifButtonNotWork", lang);// "If the button doesn't work, you can copy and paste this link into your browser:";
        String useButton = ResourceBundleUtil.getLocaleText("useButton", lang); // "We received a request to reset your password. If you did not make this request, please ignore this email. Otherwise, you can reset your password using the button below:"
        String passwordResetMailHeader = ResourceBundleUtil.getLocaleText("passwordResetMailHeader", lang); // "Password Reset Request";
        String passwordResetMailHello = ResourceBundleUtil.getLocaleText("passwordResetMailHello", lang); // "Hello";

        String mail = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <style>\n" +
                "        body {\n" +
                "            background-color: #141414;\n" +
                "            color: #ffffff;\n" +
                "            font-family: 'Arial', sans-serif;\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .email-container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #1c1c1c;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 10px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .email-header {\n" +
                "            font-size: 24px;\n" +
                "            margin-bottom: 20px;\n" +
                "            color: #fff;\n" +
                "        }\n" +
                "\n" +
                "        .email-body {\n" +
                "            font-size: 16px;\n" +
                "            line-height: 1.5;\n" +
                "            color: #cfcfcf;\n" +
                "        }\n" +
                "\n" +
                "        .email-body p {\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .reset-button {\n" +
                "            display: inline-block;\n" +
                "            padding: 15px 30px;\n" +
                "            background-color: #5078fc;\n" +
                "            color: #fff;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 18px;\n" +
                "        }\n" +
                "\n" +
                "        .reset-button:hover {\n" +
                "            background-color: #3f66e1;\n" +
                "        }\n" +
                "\n" +
                "        .email-footer {\n" +
                "            margin-top: 30px;\n" +
                "            font-size: 12px;\n" +
                "            color: #7a7a7a;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"email-container\">\n" +
                "        <h1 class=\"email-header\">" + passwordResetMailHeader + "</h1>\n" +
                "        <div class=\"email-body\">\n" +
                "            <p>" + passwordResetMailHello + ",</p>\n" +
                "            <p>" + useButton + "</p> <a href=\"" + resetPasswordHref + "\"\n" +
                "                class=\"reset-button\">" + resetPassword + "</a>\n" +
                "            <p>" + ifButtonNotWork + "</p>\n" +
                "            <p><a href=\" " + resetPasswordHref + " \" style=\"color: #5078fc;\"> " + resetPasswordHref + "</a></p>\n" +
                "        </div>\n" +
                "        <div class=\"email-footer\">\n" +
                "            <p>" + contactSupportTeam + "</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";

        return mail;

    }

}
