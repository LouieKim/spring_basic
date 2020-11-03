package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    
    //Test가 한번씩 동작할때마다 repository를 비워짐
    @AfterEach
    public void afterEach(){
        repository.clearStory();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        //계속 true인가 직접 눈으로 확인하기 힘듬
        System.out.println("result = " + (result == member));

        //Success
        //Assertions.assertEquals(member, result);

        //Fail
        //Assertions.assertEquals(member, null);

        //Success
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);

        //Fail
        //org.assertj.core.api.Assertions.assertThat(member).isEqualTo(null);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        //Shift + F6 누르면 rename 가능
        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
