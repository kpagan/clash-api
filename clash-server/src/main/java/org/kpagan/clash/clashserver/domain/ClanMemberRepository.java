package org.kpagan.clash.clashserver.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ClanMemberRepository extends JpaRepository<ClanMember, String> {

	@Query("SELECT m FROM ClanMember m WHERE m.tag NOT IN (:tags)")
	List<ClanMember> findClanMembersNotIn(@Param("tags") List<String> tags);
	
	List<ClanMember> findByClanTag(String tag);

}
