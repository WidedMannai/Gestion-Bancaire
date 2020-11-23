package org.sid.dao;

import org.sid.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("select o from Operation o where o.compte.codeCompte=:x order by o.dateOperation desc")
	public Page<Operation> listOperation(@Param("x")Long code,Pageable pageable);
    
    @Query("select o2 from Operation o2  order by o2.dateOperation desc")//nn filter ktifachw ae in9oollik kifech 5anjareb nijbid il kol w ba3ed il filter mouch s3ibbhy
	public Page<Operation> afficheOp(Pageable pageable);
      
}
