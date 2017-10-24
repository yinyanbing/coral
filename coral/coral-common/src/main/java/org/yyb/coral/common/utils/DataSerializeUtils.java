package org.yyb.coral.common.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 序列化与反序列化
 * 
 * @author yybg
 * 
 */
public class DataSerializeUtils {

	private static final Logger logger = LoggerFactory.getLogger(DataSerializeUtils.class);

	private static final String ENCODE_TYPE = "UTF-8";

	public static byte[] serializeKey(String key) {
		if (key == null) {
			return new byte[0];
		}
		byte[] result = null;
		result = stringToBytes(key);
		return result;
	}

	public static String deserializedKey(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		String result = null;
		result = bytesToString(bytes);
		return result;
	}

	public static byte[] serializeValue(Object value) {
		byte[] result = new byte[0];
		if (value == null) {
			return result;
		}
		try {
			result = objectSerialize(value);
		} catch (Exception e) {
			logger.error("Cache value serialize faild, value = " + value, e);
		}
		return result;
	}

	public static Object deserializedValue(byte[] bytes) {
		if (bytes == null) {
			return bytes;
		}
		Object result = null;
		try {
			result = objectDeserialized(bytes);
		} catch (Exception e) {
			logger.error("Cache value deserialized faild", e);
		}
		return result;
	}

	public static byte[] objectSerialize(Object object) {
		if (object != null) {
			return SerializationUtils.serialize((Serializable) object);
		}
		return new byte[0];
	}

	public static Object objectDeserialized(byte[] bytes) {
		if ((bytes != null) && (bytes.length > 0)) {
			Object result = SerializationUtils.deserialize(bytes);
			return result;
		}
		return null;
	}

	public static byte[] stringToBytes(String str) {
		try {
			return str.getBytes(ENCODE_TYPE);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("String trans to bytes fialed,String is :" + str);
		}
	}

	public static String bytesToString(byte[] bytes) {
		try {
			return new String(bytes, ENCODE_TYPE);
		} catch (UnsupportedEncodingException e) {
			// throw new
			// IllegalArgumentException("bytes trans to String fialed,bytes is
			// :"
			// + bytes);
			return null;
		}
	}

}
