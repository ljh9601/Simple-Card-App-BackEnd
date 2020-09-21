package com.bapay.publicserver.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bapay.publicserver.Domain.AddCardDomain;
import com.bapay.publicserver.Domain.DeleteCardDomain;
import com.bapay.publicserver.Domain.ModifyCardDomain;
import com.bapay.publicserver.Domain.UserLoginDomain;
import com.bapay.publicserver.Domain.ViewCardListDomain;

@Service
public class UserCardService {
    
    private Logger logger = LoggerFactory.getLogger(UserCardService.class);
    
    /*
    public ViewCardListDomain viewCardListService(ViewCardListDomain viewCardListDomain){
    	ViewCardListDomain usrCardDomain = userCardRepository.selectCardListByKeyId(viewCardListDomain);
        
		return usrCardDomain;
    }*/
    
    /**
     * @Role Return Refined Domain to show User
     * @param viewCardListDomain
     * @return
     */
    public List<ViewCardListDomain> viewCardListService(List viewCardListDomain) {
    	List<ViewCardListDomain> returnList = null;
    	
    	List<ViewCardListDomain> viewCardsDomain = viewCardListDomain;
    	
    	if (viewCardListDomain != null && viewCardsDomain.size() > 0) { 

			returnList = new ArrayList<ViewCardListDomain>();
			for (int i = 0; i < viewCardListDomain.size(); i++) {
				ViewCardListDomain returnDomain = new ViewCardListDomain();
				returnDomain.setCard_name(viewCardsDomain.get(i).getCard_name());
				returnDomain.setCard_company(viewCardsDomain.get(i).getCard_company());
				returnDomain.setCard_num(viewCardsDomain.get(i).getCard_num());
				
				//returnDomain.setCard_thru(viewCardsDomain.get(i).getCard_thru());
				returnList.add(returnDomain);
			}
		}
		return returnList;
    }
    
    public AddCardDomain addCardService(AddCardDomain addCardDomain) {
		AddCardDomain addDomain = new AddCardDomain();

		addDomain.setCardInfo_id(addCardDomain.getCardInfo_id());
		addDomain.setCard_name(addCardDomain.getCard_name());
		addDomain.setCard_company(addCardDomain.getCard_company());
		addDomain.setCard_num(addCardDomain.getCard_num());
		addDomain.setCard_cvc(addCardDomain.getCard_cvc());
		addDomain.setCard_thru(addCardDomain.getCard_thru());
		addDomain.setUser_id(addCardDomain.getUser_id());

		return addCardDomain;
    }
    
    public DeleteCardDomain deleteCardService(DeleteCardDomain deleteCardDomain) {
    	DeleteCardDomain delCardDomain = new DeleteCardDomain();
    	delCardDomain.setCardInfo_id(deleteCardDomain.getCardInfo_id());
    	
    	return delCardDomain;
    }
    
    public ModifyCardDomain modifyCardService(ModifyCardDomain modifyCardDomain) {
    	ModifyCardDomain modCardDomain = new ModifyCardDomain();
    	
    	modCardDomain.setCardInfo_id(modifyCardDomain.getCardInfo_id());
    	modCardDomain.setNew_name(modifyCardDomain.getNew_name());

		return modCardDomain;
    }
}
