package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;
//    @OneToMany(mappedBy = "team")
//    private List<Member> members = new ArrayList<>(); // 관례 : 초기화를 해놔야 null point가 뜨지 않기 때문에
//    public void addMember(Member member) {
//        member.setTeam(this);
//        members.add(member);
//    }
}