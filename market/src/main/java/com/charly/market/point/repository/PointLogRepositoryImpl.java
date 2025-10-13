package com.charly.market.point.repository;

import com.charly.market.point.model.PointLog;
import com.charly.market.point.model.dto.PointLogSearchRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PointLogRepositoryImpl implements PointLogRepositoryCustom {
    @PersistenceContext//Spring환경에서 EntityManager인스턴스를 필드에 연결
    private EntityManager em;
    @Override
    public Page<PointLog> search(PointLogSearchRequest request) {
        String baseQuery="SELECT p FROM PointLog p WHERE 1=1";
        String countQuery="SELECT Count(p) FROM PointLog p WHERE 1=1";

        StringBuilder filter = new StringBuilder();

        if(request.userId()!=null){
            filter.append(" AND p.user.id = :userId ");//p (PointLog) → user (User 객체) → id (User 객체의 ID) 순서대로 접근
        }
        if (request.bidId()!=null){
            filter.append(" AND p.auctionBid.id = :bidId ");
        }
        if(request.type()!=null && !request.type().isBlank()){
            filter.append(" AND p.type = :type");
        }
        if(request.keyword()!=null && !request.keyword().isBlank()){
            filter.append(" AND p.explanation Like :keyword");
        }

        String jpql= baseQuery + filter+" ORDER BY p."+ request.sortBy()+" "+request.direction();
        var query=em.createQuery(jpql,PointLog.class);//PointLog.class는 TypedQuery<PointLog>를 반환 한다고 알려줌
        // 이 쿼리가 반환하는 리스트는 자동으로 List<PointLog> 타입이 됨, 결과를 List<PointLog>로 강제 형 변환 할 필요가 없음
        if (request.userId()!=null){
            query.setParameter("userId", request.userId());
        }
        if (request.bidId()!=null){
            query.setParameter("bidId", request.bidId());
        }
        if(request.type()!=null && !request.type().isBlank()){
            query.setParameter("type", request.type());
        }
        if(request.keyword()!=null && !request.keyword().isBlank()){
            query.setParameter("keyword", "%"+request.keyword()+"%");
        }
        query.setFirstResult(request.page()*request.size());//시작 페이지(page num * page size)
        query.setMaxResults(request.size());// 한 페이지 표시 항목 수
        List<PointLog> results= query.getResultList();//FirstResult와 MaxResults를 기반으로 DB에 쿼리를 전송

        var countQ= em.createQuery(countQuery+filter,Long.class);// filter제약 조건에 해당하는 행의 총 개수를 저장
        if(request.userId()!=null){
            countQ.setParameter("userId", request.userId() );
        }
        if(request.bidId()!=null){
            countQ.setParameter("bidId", request.bidId());
        }
        if(request.type()!=null && !request.type().isBlank()){
            countQ.setParameter("type", request.type());
        }
        if(request.keyword()!=null && !request.keyword().isBlank()){
            countQ.setParameter("keyword", "%"+request.keyword()+"%");
        }
        long total= countQ.getSingleResult();

            return new PageImpl<>(results, PageRequest.of(request.page(),request.size()),total);
    }
}
