package com.example.lorawan.enmu;
/**
 * redis缓存中key的使用规范说明
 * */
public enum redisExplain {
	allId("所有已经设置过类型的终端设备devaddr","allId"),
	type("key为type跟devaddr值，如type8888，type417544，value记录该id设备的类型","type"),
	loraWanIp("loraWan的IP与Port,用来记录是否已连接","loraWanIp"),
	status("key为status跟devaddr值，如status8888，status417544，value记录该id设备的解析数据","status"),

	hongwaiList("所有加入过缓存的红外传感器","hongwaiList"),
	zhinengsuoList("所有加入过缓存的智能锁传感器","zhinengsuoList"),
	humitureList("所有加入过缓存的温湿度传感器","humitureList"),
	smokeList("所有加入过缓存的烟感传感器","smokeList"),
	parkingList("所有加入过缓存的地磁传感器","parkingList"),
	manholecoverList("所有加入过缓存的窨井盖传感器","manholecoverList"),
	assetsList("所有加入过缓存的资产定位传感器","assetsList"),
	
	thresholdWDMax("温度最大值","thresholdWDMax"),
	thresholdWDMin("温度最小值","thresholdWDMin"),
	thresholdSDMax("湿度最大值","thresholdSDMax"),
	thresholdSDMin("湿度最小值","thresholdSDMin"),
	
	lorawanIpAndPort("Lora服务端Ip:Port","lorawanIpAndPort"),
	lorawanAddress("Lora服务端Address","lorawanAddress"),
	lorawanRestNum("Lora服务端重连次数","lorawanRestNum");
	
	public String name;
	public String explain;
	private redisExplain(String name, String explain){
		this.name = name;
		this.explain = explain;
	}
}
