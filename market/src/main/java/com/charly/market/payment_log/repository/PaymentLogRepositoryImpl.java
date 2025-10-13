package com.charly.market.payment_log.repository;

import com.charly.market.payment_log.model.dto.PaymentLogSearchRequest;
import com.charly.market.payment_log.model.entity.PaymentLog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentLogRepositoryImpl implements PaymentLogRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<PaymentLog> search(PaymentLogSearchRequest request) {

        String baseQuery="SELECT p FROM PaymentLog p WHERE 1=1";
        String countQuery="SELECT Count(p) FROM PaymentLog p WHERE 1=1";

        StringBuilder filter=new StringBuilder();
        if(request.userId()!=null){
            filter.append(" AND p.user.id =:userId ");
        }
        if (request.accountId()!=null) {
            filter.append(" AND p.account.id =:auctionId ");
        }
        if (request.type()!=null && !request.type().isBlank()) {
            filter.append(" AND p.type =:type ");
        }
        if (request.paymentAmount()!=null){
            filter.append(" AND p.paymentAmount >:paymentAmount ");//결제 금액보다 큰 이력만 출력
        }

        String jpql= baseQuery + filter + " ORDER BY p."+ request.sortBy()+ " " +request.direction();
        var query=em.createQuery(jpql,PaymentLog.class);

        if(request.userId()!=null){
            query.setParameter("userId", request.userId());
        }
        if(request.accountId()!=null){
            query.setParameter("auctionId", request.accountId());
        }
        if(request.type()!=null){
            query.setParameter("type", request.type());
        }
        if(request.paymentAmount()!=null){
            query.setParameter("paymentAmount", request.paymentAmount());
        }

        query.setFirstResult(request.page()*request.size());
        query.setMaxResults(request.size());
        List<PaymentLog> results=query.getResultList();

        var countQ= em.createQuery(countQuery+filter,Long.class);

        if(request.userId()!=null){
            countQ.setParameter("userId", request.userId());
        }
        if(request.accountId()!=null){
            countQ.setParameter("auctionId", request.accountId());
        }
        if(request.type()!=null){
            countQ.setParameter("type", request.type());
        }
        if(request.paymentAmount()!=null){
            countQ.setParameter("paymentAmount", request.paymentAmount());
        }

        long total=countQ.getSingleResult();
        return new PageImpl<>(results, PageRequest.of(request.page(),request.size()), total);
    }
}
