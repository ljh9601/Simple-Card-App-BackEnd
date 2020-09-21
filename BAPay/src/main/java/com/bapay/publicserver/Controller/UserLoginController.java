package com.bapay.publicserver.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bapay.publicserver.Domain.UserLoginDomain;
import com.bapay.publicserver.Domain.ViewCardListDomain;
import com.bapay.publicserver.Response.ResponseDataMap;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(value="user")
public class UserLoginController {
	
	Environment environment;
	private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);
    
 
    //@Autowired
    //UserCardService userCardService;
    
    @RequestMapping("/")
    public String index() {
 
        return "index";
    }
    
    /**
     * @Made 2020.09.13
     * @Role User Login
     * @param usrLoginDomain
     * @param bindingResult
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody JsonNode LoginController(
			@Valid @RequestBody UserLoginDomain usrLoginDomain, BindingResult bindingResult,
			HttpServletRequest request, HttpServletRequest response) throws Exception{
    	
    	ResponseDataMap responseMap = new ResponseDataMap(environment);
    	UserLoginDomain retDomain = new UserLoginDomain();
    	logger.info(usrLoginDomain.toString());
    	
    	URL url = new URL ("http://27.96.130.32:8000/UCController/uc/viewCards/?");
    	//URL nameUrl = new URL("http://27.96.130.32:8000/UCController/uc/sendName/?");
    	
    	HttpURLConnection con = (HttpURLConnection)url.openConnection();
    	
    	con.setConnectTimeout(500);
    	con.setRequestMethod("POST");
    	con.setRequestProperty("Content-Type", "application/json; utf-8");
    	con.setRequestProperty("Accept", "application/json");
    	
    	con.setDoOutput(true);
    	
    	String c = "{\"user_id\" : \"u01\""  + "}";
    	logger.info(c);
    	
    	List<ViewCardListDomain> cardList;
    	Map<String, Object> m = new HashMap<String, Object>();
    	Map<String, Object> names = new HashMap<String, Object>();
    	
    	JsonBuilderFactory factory = Json.createBuilderFactory(m);
    	JsonBuilderFactory nameFactory = Json.createBuilderFactory(names);
    	JsonObjectBuilder obj = factory.createObjectBuilder();
    	
    	
    	obj.add("user_id", usrLoginDomain.getUser_id());
    	
    	
    	String te = obj.build().toString();
    	logger.info("te : " + te);
    	try(OutputStream os = con.getOutputStream()) {
    	    byte[] input = c.getBytes("utf-8");
    	    logger.info(input.toString());
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
    	

    	logger.info(json);
    	
    	/*List<ViewCardListDomain> retCards;
    	List<ViewCardListDomain> viewCardListDomain;
    	
    	try {
    		retCards = userCardService.viewCardListService(viewCardListDomain);
    		
    	}*/
    	// JSONParser로 객체 생성
    	//JSONParser parser = new JSONParser(con.getInputStream());
    	//obj 객체에 String 형식을 JSONParser형식으로 바꿔 넣는다
    	//Object res = parser.parse();
    	// JSONObject로 변환
    	//JSONObject jsonObj = (JSONObject) res;
    	
    	
    	return root;
    }
}
