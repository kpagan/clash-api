package org.kpagan.clash.clashserver.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ClanMemberRepository extends JpaRepository<ClanMember, String> {

	List<ClanMember> findByTagNotIn(@Param("tags") List<String> tags);
	
	List<ClanMember> findByClanTag(String tag);

}
