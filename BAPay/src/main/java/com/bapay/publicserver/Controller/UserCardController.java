package com.bapay.publicserver.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bapay.publicserver.Domain.AddCardDomain;
import com.bapay.publicserver.Domain.DeleteCardDomain;
import com.bapay.publicserver.Domain.ModifyCardDomain;
import com.bapay.publicserver.Domain.ViewCardListDomain;
import com.bapay.publicserver.Response.ResponseDataMap;
import com.bapay.publicserver.Service.UserCardService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 *  # 응답코드 정리:
	# 0000 : 카드 추가 성공
	# 0001 : 카드 추가 실패
	# 0010 : 카드 삭제 성공
	# 0011 : 카드 삭제 실패
 *
 */



@RestController
@RequestMapping(value="/card")
public class UserCardController {
	private static final Logger logger = LoggerFactory.getLogger(UserCardController.class);
    @Autowired
    UserCardService userCardService;
    
    @Inject
	private Environment environment;

    
    /**
     * @Role Show Card List to Users
     * @Made 2020.09.14
     * @param viewCardListDomain
     * @param bindingResult
     * @param request
     * @param response
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/viewCards", method = RequestMethod.POST)
    public @ResponseBody ResponseDataMap ViewUserCardController(
    		@Valid @RequestBody ViewCardListDomain viewCardListDomain, BindingResult bindingResult,
			HttpServletRequest request, HttpServletRequest response) throws IOException {
    	
    	ResponseDataMap responseMap = new ResponseDataMap(environment);
    	
    	URL url = new URL ("http://27.96.130.32:8000/UCController/uc/viewCards/?");
    	HttpURLConnection con = (HttpURLConnection)url.openConnection();
    	con.setRequestMethod("POST");
    	con.setRequestProperty("Content-Type", "application/json; utf-8");
    	con.setRequestProperty("Accept", "application/json");

    	con.setDoOutput(true);
    	
    	//ViewCardListDomain retDomain = userCardService.viewCardListService(viewCardListDomain);
    	
    	Map<String, Object> m = new HashMap<String, Object>();
    	
    	JsonBuilderFactory factory = Json.createBuilderFactory(m);
    	
    	JsonObjectBuilder obj = factory.createObjectBuilder();
    	obj.add("user_id", viewCardListDomain.getUser_id());
    	
    	String te = obj.build().toString();
    	//Object bodyPart = responseMap.getBody();
    	//logger.info(bodyPart.toString());
    	/*JSONObject json = new JSONObject();
    	
    	json.put("cardInfo_id", retDomain.getCardInfo_id());
    	json.put("card_name", retDomain.getCard_name());
    			
    			
    	json.put("card_company", retDomain.getCard_company());
    	json.put("card_num", retDomain.getCard_num());
    	json.put("card_cvc", retDomain.getCard_cvc());
    	json.put("card_thru", retDomain.getCard_thru());*/
    	//String temp = bodyPart.toString();
    	
    	//logger.info(temp);
    	try(OutputStream os = con.getOutputStream()) {
    	    byte[] input = te.getBytes("utf-8");
    	    os.write(input, 0, input.length);			
    	}
    	BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
    	String output, json = "";
    	while ((output = br.readLine()) != null) {
    		logger.info(output);
    		json += output;
    	}
    	logger.info(json);
    	/*
    	try(BufferedReader br = new BufferedReader(
    			  new InputStreamReader(con.getInputStream(), "utf-8"))) {
    			    StringBuilder responsed = new StringBuilder();
    			    String responseLine = null;
    			    while ((responseLine = br.readLine()) != null) {
    			    	//logger.info(responseLine);
    			        responsed.append(responseLine.trim());
    			    }
    			}
    	catch(Exception e) {
    		logger.info(e.toString());
    	}*/
    	
    	/*
    	List<ViewCardListDomain> retDomain = userCardService.viewCardListService(viewCardListDomain);
    	if (CheckCardListDomain(viewCardListDomain) == false) {
    		responseMap.putTail("code", 0);
    		return responseMap;
    	}
    	try {
    		responseMap.putData("cardListInfo", retDomain);
    		responseMap.putTail("code", 1);
    	}catch(Exception e) {
    		responseMap.putTail("code", 0);
    	}
    	
    	responseMap.putData("cardListInfo", viewCardListDomain);
    	*/
    	return responseMap;
    }
    
	public boolean CheckCardListDomain(List<ViewCardListDomain> viewCardListDomain) {
		for (int i = 0; i < viewCardListDomain.size(); i++) {
			//logger.info(viewCardListDomain.get(i).toString());
			if (viewCardListDomain.get(i).getCardInfo_id() == "" || viewCardListDomain.get(i).getCard_name() == "" 
					|| viewCardListDomain.get(i).getCard_company() == "" || viewCardListDomain.get(i).getCard_num() == "" 
					|| viewCardListDomain.get(i).getCard_cvc() == 0 || viewCardListDomain.get(i).getCard_thru() == "") {
				return false;
			}
		}
		return true;
	}
    
    /**
     * @Role Show Card List to Authenticated User
     * @param usrLoginDomain
     * @param bindingResult
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    /*
    @RequestMapping(value = "/viewCards", method = RequestMethod.POST)
    public @ResponseBody ViewCardListDomain ViewUserCardController(
			@Valid @RequestBody ViewCardListDomain viewCardListDomain, BindingResult bindingResult,
			HttpServletRequest request, HttpServletRequest response) throws Exception{
    	logger.error("Controller first : " + viewCardListDomain.toString());
    	ViewCardListDomain retCardDomain = null;
    	retCardDomain = userCardService.viewCardListService(viewCardListDomain);
    	
    	return retCardDomain;
    }*/
    
    /**
     * @Role Send Request for Adding Card to Private Server
     * @Made 2020.09.14
     * @param addCardDomain
     * @param bindingResult
     * @param request
     * @param response
     * @return code
     * @throws IOException 
     */
    @RequestMapping(value="/addCard", method = RequestMethod.POST)
    public @ResponseBody JsonNode AddCardController(
    		@Valid @RequestBody AddCardDomain addCardDomain, BindingResult bindingResult,
			HttpServletRequest request, HttpServletRequest response) throws IOException {
    	
    	ResponseDataMap responseMap = new ResponseDataMap(environment);
    	URL url = new URL ("http://27.96.130.32:8000/UCController/uc/addCard/?");
    	HttpURLConnection con = (HttpURLConnection)url.openConnection();
    	con.setRequestMethod("POST");
    	con.setRequestProperty("Content-Type", "application/json; utf-8");
    	con.setRequestProperty("Accept", "application/json");

    	con.setDoOutput(true);
    	
    	AddCardDomain retDomain = addCardDomain;
    	logger.info("ret" + retDomain.toString());
    	Map<String, Object> m = new HashMap<String, Object>();
    	
    	JsonBuilderFactory factory = Json.createBuilderFactory(m);
    	JsonObjectBuilder obj = factory.createObjectBuilder();
     	
    	obj.add("cardInfo_id", retDomain.getCardInfo_id());
    	obj.add("card_name", retDomain.getCard_name());
    	obj.add("card_company", retDomain.getCard_company());
    	obj.add("card_num", retDomain.getCard_num());
    	obj.add("card_cvc", retDomain.getCard_cvc());
    	obj.add("card_thru", retDomain.getCard_thru());
    	obj.add("user_id", retDomain.getUser_id());
    	
    	String te = obj.build().toString();
    	
    	try(OutputStream os = con.getOutputStream()) {
    	    byte[] input = te.getBytes("utf-8");
    	    os.write(input, 0, input.length);			
    	}

    	String json = "";
    	BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
    	String output;
    	while ((output = br.readLine()) != null) {
    	   json += output;
    	}
    	con.disconnect();
    	
    	//String to JsonNode
    	ObjectMapper objectMapper = new ObjectMapper();
    	JsonNode root = objectMapper.readTree(json);
    	
    	logger.info(root.asText());
    	return root;
    }
    
    /**
     * @Role Send Request for Deleting Card to Private Server
     * @Made 2020.09.14
     * @param deleteCardDomain
     * @param bindingResult
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/deleteCard", method = RequestMethod.DELETE)
    public @ResponseBody ResponseDataMap DeleteCardControlle(
    		@Valid @RequestBody DeleteCardDomain deleteCardDomain, BindingResult bindingResult,
			HttpServletRequest request, HttpServletRequest responset) throws IOException{

    	ResponseDataMap responseMap = new ResponseDataMap(environment);
    	URL url = new URL ("http://27.96.130.32:8000/UCController/uc/delCard/?");
    	HttpURLConnection con = (HttpURLConnection)url.openConnection();
    	con.setRequestMethod("DELETE");
    	con.setRequestProperty("Content-Type", "application/json; utf-8");
    	con.setRequestProperty("Accept", "application/json");

    	con.setDoOutput(true);
    	
    	String temp = "{\"cardInfo_id\" : \"KB01\"}";
    	
    	
    	try(OutputStream os = con.getOutputStream()) {
    	    byte[] input = temp.getBytes("utf-8");
    	    os.write(input, 0, input.length);			
    	}
    	

    	try(BufferedReader br = new BufferedReader(
    			  new InputStreamReader(con.getInputStream(), "utf-8"))) {
    			    StringBuilder responsed = new StringBuilder();
    			    String responseLine = null;
    			    while ((responseLine = br.readLine()) != null) {
    			    	logger.info(responseLine);
    			        responsed.append(responseLine.trim());
    			    }
    			}
    	
    	return responseMap;
    }
    
    
    /**
     * {
	"user_id" : "haeng",
	"cardInfo_id" : "KB01",
	"card_name" : "KB_mylove",
	"card_company" : "KB",
	"card_num" : "0000-0012-1230-1231",
	"card_cvc" : "123",
	"card_thru" : "11/27"
	}
     * @throws IOException 
     */

    
    /**
     * @Role Send Request for Modifying Card Info to Private Server 
     * @param modifyCardDomain
     * @param bindingResult
     * @param request
     * @param response
     * @return
     * @throws IOException 
     */
    @RequestMapping(value="/modifyCard", method = RequestMethod.POST)
    public @ResponseBody JsonNode ModifyCardController(
    		@Valid @RequestBody ModifyCardDomain modifyCardDomain, BindingResult bindingResult,
			HttpServletRequest request, HttpServletRequest response) throws IOException {
    	
    	ModifyCardDomain modCardDomain = userCardService.modifyCardService(modifyCardDomain);
    	URL url = new URL ("http://27.96.130.32:8000/UCController/uc/changeNick/?");
    	HttpURLConnection con = (HttpURLConnection)url.openConnection();
    	con.setRequestMethod("POST");
    	con.setRequestProperty("Content-Type", "application/json; utf-8");
    	con.setRequestProperty("Accept", "application/json");

    	con.setDoOutput(true);
    	
    	logger.info("mod" + modCardDomain.toString());
    	Map<String, Object> m = new HashMap<String, Object>();
    	
    	JsonBuilderFactory factory = Json.createBuilderFactory(m);
    	JsonObjectBuilder obj = factory.createObjectBuilder();
     	
    	obj.add("new_name", modCardDomain.getNew_name());
    	obj.add("cardInfo_id", modCardDomain.getCardInfo_id());
    	
    	String te = obj.build().toString();
    	
    	try(OutputStream os = con.getOutputStream()) {
    	    byte[] input = te.getBytes("utf-8");
    	    os.write(input, 0, input.length);			
    	}

    	String json = "";
    	BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
    	String output;
    	while ((output = br.readLine()) != null) {
    	   json += output;
    	}
    	con.disconnect();
    	
    	//String to JsonNode
    	ObjectMapper objectMapper = new ObjectMapper();
    	JsonNode root = objectMapper.readTree(json);
    	
    	logger.info(root.asText());
    	return root;
    }
}
