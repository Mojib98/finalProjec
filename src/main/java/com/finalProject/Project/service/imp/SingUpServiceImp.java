package com.finalProject.Project.service.imp;

import com.finalProject.Project.entity.Avatar;
import com.finalProject.Project.entity.ConfirmationToken;
import com.finalProject.Project.entity.Customer;
import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.dto.UserDto;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.exception.UnActiveToken;
import com.finalProject.Project.repository.interfaces.ConfirmationTokenRepository;
import com.finalProject.Project.repository.interfaces.SingUpRepository;
import com.finalProject.Project.service.interfaces.SingUpService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SingUpServiceImp  implements SingUpService {
   private final SingUpRepository singUpRepository;
   private final ModelMapper modelMapper = new ModelMapper();
   private final EmailService emailService;
   private final ConfirmationTokenRepository confirmationTokenRepository;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void requestForSingUp(UserDto userDto) throws IOException {
   /*     modelMapper.addMappings(new PropertyMap<UserDto, Expert>() {
            @Override
            protected void configure() {
//                skip(destination.getAvatar());
                skip(source.getImage());
            }
        });*/
        String tokenCode = "EXPERT"+UUID.randomUUID().toString();
        Expert expert = modelMapper.map(userDto, Expert.class);

        String password = bCryptPasswordEncoder.encode(userDto.getPassword());
        expert.setPassword(password);
        ConfirmationToken token = new ConfirmationToken(tokenCode,expert);
        expert.setStatus(UserStatus.AWAITING_CONFIRMATION_EMAIL);
        expert.setWallet(0);
        expert.setRate(5F);
        expert.setAvatar(new Avatar(userDto.getImage().getBytes()));
        singUpRepository.save(expert);
        confirmationTokenRepository.save(token);
        String link = "http://localhost:8080/singup/confirm?token=" + tokenCode;
        emailService.send(
                userDto.getEmail(),
                buildEmail(userDto.getFirstName(), link));
    }

    @Override
    @Transactional
    public void insertCustomer(UserDto userDto) {
        Customer customer = modelMapper.map(userDto, Customer.class);
        customer.setStatus(UserStatus.AWAITING_CONFIRMATION_EMAIL);
        customer.setWallet(50000);
        String password = bCryptPasswordEncoder.encode(userDto.getPassword());
        customer.setPassword(password);
        String tokenCode = UUID.randomUUID().toString();
        ConfirmationToken token = new ConfirmationToken(tokenCode,customer);
        String link = "http://localhost:8080/singup/confirm?token=" + tokenCode;
        emailService.send(
                userDto.getEmail(),
                buildEmail(userDto.getFirstName(), link));
        singUpRepository.save(customer);
        confirmationTokenRepository.save(token);
    }
    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire after use. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByTokenCode(token).
                orElseThrow(() ->
                        new UnActiveToken("token not found"));
        if (!confirmationToken.getIsActive())
            throw new UnActiveToken("token is expired");

        confirmationTokenRepository.updateConfirmedAt(token);
        if (token.substring(0,6).equals("EXPERT"))
            singUpRepository.updadeStatusUser(UserStatus.AWAITING_CONFIRMATION,
                    confirmationToken.getUser().getEmail());
         else
        singUpRepository.updadeStatusUser(UserStatus.ACTIVE,
                        confirmationToken.getUser().getEmail());
        return "confirmed";
    }


}
