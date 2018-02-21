package com.emcs.busniess.common;

        import com.emcs.supers.PubService;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Propagation;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
@Service
public class UpdateCmAcctTranSeq extends PubService {
    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    @Override
    public void process(Map<String, Object> data) {
//        数据封装带补充
        oneDML.updateCmAcctTranSeq(data);
    }
}
