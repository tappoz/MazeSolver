package org.tappoz.util;

public enum CoordinateTypeEnum {
	
	WALL(1), PASSAGE(0);
	private int code;
	
	private CoordinateTypeEnum(int inputCode) {
		this.setCode(inputCode);
	}
	
	// --------------------------------------------------
	
	public int getCode() {
		return code;
	}

	public void setCode(int inputCode) {
		this.code = inputCode;
	}
	
	// --------------------------------------------------
	
	public static boolean isMember(int inputValue) 
	{
		CoordinateTypeEnum[] allCoordinateTypes = CoordinateTypeEnum.values();
		for (CoordinateTypeEnum thisCoordinateType : allCoordinateTypes)
			if (thisCoordinateType.code == inputValue)
				return true;
       	return false;
	}
	
	public static CoordinateTypeEnum getEnumValue(int inputValue) throws UnsupportedOperationException
	{
		CoordinateTypeEnum[] allCoordinateTypes = CoordinateTypeEnum.values();
		for (CoordinateTypeEnum thisCoordinateType : allCoordinateTypes)
			if (thisCoordinateType.code == inputValue)
				return thisCoordinateType;
		throw new UnsupportedOperationException("This inputValue is not valid!");
	}

}
