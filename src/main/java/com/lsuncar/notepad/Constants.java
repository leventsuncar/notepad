package com.lsuncar.notepad;

public interface Constants {

    public static final String RESET_PASSWORD_MAIL = "<!DOCTYPE html> <html lang=\"en\"> <head> <meta charset=\"UTF-8\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> <style> body { background-color: #141414; color: #ffffff; font-family: 'Arial', sans-serif; padding: 20px; } .email-container { max-width: 600px; margin: 0 auto; background-color: #1c1c1c; padding: 20px; border-radius: 10px; text-align: center; } .email-header { font-size: 24px; margin-bottom: 20px; color: #fff; } .email-body { font-size: 16px; line-height: 1.5; color: #cfcfcf; } .email-body p { margin-bottom: 20px; } .reset-button { display: inline-block; padding: 15px 30px; background-color: #5078fc; color: #fff; text-decoration: none; border-radius: 5px; font-size: 18px; } .reset-button:hover { background-color: #3f66e1; } .email-footer { margin-top: 30px; font-size: 12px; color: #7a7a7a; } </style> </head> <body> <div class=\"email-container\"> <h1 class=\"email-header\">Password Reset Request</h1> <div class=\"email-body\"> <p>Hello,</p> <p>We received a request to reset your password. If you did not make this request, please ignore this email. Otherwise, you can reset your password using the button below:</p> <a href=\"{0}/respwd/{1}\" class=\"reset-button\">Reset Password</a> <p>If the button doesn't work, you can copy and paste this link into your browser:</p> <p><a href=\"{0}/respwd/{1}\" style=\"color: #5078fc;\">{0}/respwd/{1}</a></p> </div> <div class=\"email-footer\"> <p>If you have any questions, feel free to contact our support team.</p> </div> </div> </body> </html>";


}
