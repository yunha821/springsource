package com.example.project3.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "members")
@Entity
public class Team {

    @Id
    private String id;

    private String name;

    // @OneToMany -> left join 을 하지 않음 => member 정보 없음
    // , fetch = FetchType.EAGER
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @Builder.Default
    // Builder가 안해줘서 객체 생성 직접 진행
    private List<Member> members = new ArrayList<>();

}
