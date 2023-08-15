package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    // JPA는 EntityManager가 필요
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository); // 스프링 빈에 등록된 리포지토리를 memberService에 넣어줌
    }

/*    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository(); // 메모리를 스프링 빈으로 이용
//        return new JdbcMemberRepository(dataSource); // Jdbc를 스프링 빈으로 이용
//        return new JdbcTemplateMemberRepository(dataSource); // JdbcTemplate
        return new JpaMemberRepository(em); // Jpa

    }*/
}
