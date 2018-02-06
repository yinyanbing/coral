package org.yyb.coral.chain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息context，链式调用转换context
 * 
 * @author: yybg
 * @date: 2017年9月26日 下午2:49:47
 */
public class MessageContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4904695141260833272L;
	private transient Object origin;
	private String messageId;
	private Object content;// 流程链处理内容,如果为空则不能继续处理
	private int priority; // 等级
	private Date createDate;

	private Map<String, Object> props = null;// 扩展属性等

	public MessageContext() {
		// props = new HashMap<String, Object>();
		this.priority = 4;
		this.createDate = new Date();
	}

	public Object getOrigin() {
		return origin;
	}

	public void setOrigin(Object origin) {
		this.origin = origin;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public Object getProperty(String key) {
		if (props == null)
			return null;
		return props.get(key);
	}

	public void putProperty(String key, Object prop) {
		if (props == null) {
			// 链式单线程操作，非多线程
			props = new HashMap<String, Object>();
		}
		props.put(key, prop);
	}

	public void removeProperty(String key) {
		if (props == null)
			return;
		props.remove(key);
	}
}
