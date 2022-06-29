package customer;

import java.util.List;

public interface CustomerService {
	//CRUD
	//신규고객등록(Create:insert)
	void customer_insert(CustomerVO vo);
	//고객목록조회(Read:select)
	List<CustomerVO> customer_list();
	//고객상세조회(Read:select)
	CustomerVO customer_detail(int id);
	//고객정보변경저장(Update:update)
	void customer_update(CustomerVO vo);
	//고객정보삭제(Delete:delete)
	void customer_delete(int id);
}
