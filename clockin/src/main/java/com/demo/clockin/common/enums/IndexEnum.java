package com.demo.clockin.common.enums;

public interface IndexEnum<E extends Enum<E> & IndexEnum<E>> {
	/**
	 * 返回自定义枚举的序号,并非Enum.ordinal()
	 * @return
	 */
	public Integer getOrdinal();

	/**
	 * 返回自定义枚举的名称,并非Enum.name();
	 * @return
	 */
	public String getName();
	
	
}
