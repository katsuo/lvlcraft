package me.samkio.lcrange;


public enum EnumBowMaterial
{
  STANDARD("Normal",0),
  ICE("Ice",1),
  FIRE("Fire",2),
  THRICE("Thrice",3),
  TORCH("Torch",4),
  ROCKET("Explosive",5),
  WATER("Water",6),
  LIGHT("Lightning",7);

  private String name;
  private short data;
  private int value;


  private EnumBowMaterial(String name,int value) {
    this.name = name;
    this.data = (ArrowHandler.lastData++);
    this.value = value;
  }

  public String getName()
  {
    return this.name;
  }

  public short getDataValue() {
    return this.data;
  }
  public int getValue(){
	  return this.value;
  }


  public static EnumBowMaterial fromData(short data) {
    for (EnumBowMaterial material : values()) {
      if (material.data == data) {
        return material;
      }
    }
    return STANDARD;
  }

  public static EnumBowMaterial fromName(String name) {
    for (EnumBowMaterial material : values()) {
      if (material.name.toLowerCase().startsWith(name.toLowerCase())) {
        return material;
      }
    }
    return STANDARD;
  }
}