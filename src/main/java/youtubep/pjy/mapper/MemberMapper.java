package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import youtubep.pjy.domain.Member;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO memberp (id,password,email) values (#{id},#{password},#{email})")
    void save(Member member);

    @Select("SELECT COUNT(*) FROM memberp WHERE id = #{id} and password = #{password}")
    Optional<Integer> findUser(Member member);

    @Select("SELECT * FROM memberp WHERE id = #{id}")
    Optional<Member> findById(String id);

    @Select("SELECT * FROM memberp WHERE email = #{email}")
    Optional<Member> findByEmail(String email);

    @Select("SELECT * FROM memberp")
    List<Member> findAll();
}
