package com.example.lorawan.until;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.Map.Entry;

/**
 * JsonUtil
 * 提供Json和对象之间的转换。
 * @author Kingo.Liang
 *
 */
public class JSonUtil {
	
//	private final static ObjectMapper mapper = new ObjectMapper();
	/**
	 * 把对象转换成Json字符串。
	 * @param obj 需要转换的对象。
	 * @return 转换好的字符串。如果出错会抛出RuntimeException
	 */
	public static String toJSonString(Object obj){
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
	public static Map<String,String> toMap(String jsonStr){
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		Map<String, String> retMap2 = gson.fromJson(jsonStr,new TypeToken<Map<String, String>>() { }.getType());
		return retMap2;
	}
	/**
	 * 把Json字符串转换成对象
	 * @param <T> 所要转换的对象类型
	 * @param json Json字符串
	 * @param clazz 转换对象有类型
	 * @return 转换好的对象，如果出错会抛出RuntimeException
	 */
	public static <T> T toObject(String json, Class<T> clazz){
		if(isJson( json ) == false){
			return null;
		}
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}
	
	/**
	 * 获取json字符串中某一节点对应的json字符串
	 * @param srcJson  源json
	 * @param nodeName  源json中的某个节点名
	 * @return  节点对应的json字符串   如果没有返回空
	 */
	public static String getNodeJson(String srcJson, String nodeName){
		return getNodeJson( srcJson,  nodeName, false);
	}
	
	public static String getNodeJson(String srcJson, String nodeName, boolean isFindArray){
		if(isJson( srcJson ) == false){
			return null;
		}
		Gson gson = new Gson();
		JsonParser jp = new JsonParser();
		JsonElement src = jp.parse(srcJson);
		JsonElement jsonElement = findJsonElement(src,  nodeName, isFindArray );
		return gson.toJson(jsonElement);
	}
	
	/**
	 * 获取json字符串中某一节点对应的字符值
	 * @param srcJson  源json
	 * @param nodeName  源json中的某个节点名
	 * @return  节点对应的json字符串   如果没有返回空
	 */
	public static String getNodeTextValue(String srcJson, String nodeName){
		return getNodeTextValue( srcJson, nodeName, false);
	}
	
	
	/**
	 * 获取json字符串中某一节点对应的字符值
	 * @param srcJson  源json
	 * @param nodeName  源json中的某个节点名
	 * @return  节点对应的json字符串   如果没有返回空
	 */
	public static String getNodeTextValue(String srcJson, String nodeName, boolean isFindArray){
		if(isJson( srcJson ) == false){
			return null;
		}
		JsonParser jp = new JsonParser();
		JsonElement src = jp.parse(srcJson);
		JsonElement jsonElement = findJsonElement(src,  nodeName, isFindArray);
		if(jsonElement==null){
			return null;
		}
		return jsonElement.getAsString();
	}
	
	/**
	 * 获取json字符串中某一节点对应的原对象
	 * @param srcJson  源json
	 * @param nodeName  源json中的某个节点名
	 * @param clazz  对应的原对象的class类型
	 * @return 节点对应的json对应的原对象   如果没有返回空
	 */
	public static <T> T getNodeToObject(String srcJson, String nodeName, Class<T> clazz){
		return getNodeToObject( srcJson,  nodeName,  clazz,  false);
	}
	
	/**
	 * 获取json字符串中某一节点对应的原对象
	 * @param srcJson  源json
	 * @param nodeName  源json中的某个节点名
	 * @param clazz  对应的原对象的class类型
	 * @return 节点对应的json对应的原对象   如果没有返回空
	 */
	public static <T> T getNodeToObject(String srcJson, String nodeName, Class<T> clazz, boolean isFindArray){
		if(isJson( srcJson ) == false){
			return null;
		}
		JsonParser jp = new JsonParser();
		JsonElement src = jp.parse(srcJson);
		JsonElement jsonElement = findJsonElement(src,  nodeName, isFindArray);
		Gson gson = new Gson();
		return gson.fromJson(jsonElement, clazz);
	}
	
	
	/**
	 * 获取json字符串中某一节点对应的原对象list
	 * @param srcJson  源json
	 * @param nodeName  源json中的某个节点名
	 * @param clazz  对应的原对象的class类型
	 * @return 节点对应的json对应的原对象   如果没有返回空
	 */
	public static <T> List<T> getList(String srcJson,  String nodeName, Class<T> objClass){
		if(isJson( srcJson ) == false){
			return null;
		}
		String json =  getNodeJson( srcJson,  nodeName);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		return getList( json, objClass);
	}
	
	
	public static boolean isJson(String srcJson){
		boolean flag = true;
		try{
			JsonParser jp = new JsonParser();
			jp.parse(srcJson);
		}catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	
	/**
	 * 将json字符串转换成list对象
	 * @param srcJson
	 * @param objClass
	 * @return
	 */
	public static <T> List<T> getList(String srcJson, Class<T> objClass){
		try {
			Gson gson = new Gson();
	        JsonParser jp = new JsonParser();
			JsonElement src = jp.parse(srcJson);
			if(src.isJsonArray() == false){
				return null;
			}
			JsonArray jsonArray = src.getAsJsonArray();
			if(jsonArray == null || jsonArray.size() < 1){
				return null;
			}
			List<T> list = new ArrayList<T>();
			for(int i = 0; i < jsonArray.size() ; i++){
				JsonElement en = jsonArray.get(i);
				list.add(gson.fromJson(en, objClass) );
			}
	        return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static JsonElement findJsonElement(JsonElement json, String notName, boolean isFindArray){
		if(json == null || json.isJsonNull() || json.isJsonPrimitive()){
			return null;
		}
		JsonElement temp = null;
		if(json.isJsonObject()){
			temp = presJsonObject(json.getAsJsonObject(), notName, isFindArray);
		}
		if(json.isJsonArray() && isFindArray == true){
			JsonArray jsonArray = json.getAsJsonArray();
			temp = presArray( jsonArray,  notName, isFindArray);
		}
		return temp;
	}
	
	private static JsonElement presArray(JsonArray jsonArray, String notName, boolean isFindArray){
		if(jsonArray == null || jsonArray.size() < 1){
			return null;
		}
		JsonElement dest = null;
		for(int i = 0; i < jsonArray.size() ; i++){
			JsonElement en = jsonArray.get(i);
			dest = findJsonElement( en, notName, isFindArray);
			if(dest != null){
				break;
			}
		}
		return dest;
	}
	
	private static JsonElement presJsonObject(JsonObject jsonObj,String notName, boolean isFindArray){
		Set<Entry<String, JsonElement>> set = jsonObj.entrySet();
		if(set.isEmpty()){
			return null;
		}
		JsonElement dest = null;
		Iterator<Entry<String, JsonElement>> iterator = set.iterator();
		while(iterator.hasNext()){
			Entry<String, JsonElement> entry = iterator.next();
			JsonElement jsonElement = entry.getValue();
			if(entry.getKey().trim().equals(notName)){
				dest = jsonElement;
			}else{
				dest = findJsonElement( jsonElement,  notName, isFindArray);
			}
			if(dest != null){
				break;
			}
		}
		return dest;
	}
	public static String getNodeTextStringValue(String srcJson, String nodeName){
		if(isJson( srcJson ) == false){
			return null;
		}
		JsonParser jp = new JsonParser();
		JsonElement src = jp.parse(srcJson);
		JsonElement jsonElement = findJsonElement(src,  nodeName, false);
		if(jsonElement==null){
			return null;
		}
		return jsonElement.toString();
	}
}
