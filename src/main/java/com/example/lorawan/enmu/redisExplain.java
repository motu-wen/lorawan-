package com.example.lorawan.enmu;
/**
 * redis缓存中key的使用规范说明
 * */
public enum redisExplain {
	type("key为type跟devaddr值，如type8888，type417544，value记录该id设备的类型","type"),
	hongwaiList("所有加入过缓存的红外传感器","hongwaiList"),
	LockList("所有加入过缓存的智能锁传感器","zhinengsuoList"),
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
