package manager;

import javax.mail.MessagingException;

/**
 * Use this class to make mail test .
 */
public class MailTest {

	public static void main(String[] args) {
		try {
			MailHelper.sendMail("avioba89@gmail.com", "test", "test");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
