package com.orionmiz.guildweb.persistence;

import com.orionmiz.guildweb.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {
}
