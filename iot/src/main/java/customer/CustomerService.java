package customer;

import java.util.List;

public interface CustomerService {
	//CRUD
	//�ű԰����(Create:insert)
	void customer_insert(CustomerVO vo);
	//�������ȸ(Read:select)
	List<CustomerVO> customer_list();
	//������ȸ(Read:select)
	CustomerVO customer_detail(int id);
	//��������������(Update:update)
	void customer_update(CustomerVO vo);
	//����������(Delete:delete)
	void customer_delete(int id);
}
