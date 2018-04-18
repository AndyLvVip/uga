package uga;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uga.basis.auth.vo.LoginVo;

import java.util.Date;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String index() {
        return new SayHello().sayHello();
    }

    @RequestMapping("/auth")
    @Secured({"abc", "efg"})
    public String auth() {
        return "authenticated!";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginVo vo) {
        return Jwts.builder()
                .setSubject(vo.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, "JwtSecretKey")
                .compact()
                ;
    }
}
