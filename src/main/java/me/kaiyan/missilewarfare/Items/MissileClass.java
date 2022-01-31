package me.kaiyan.missilewarfare.Items;

public class MissileClass {
    public float speed;
    public int range;
    public double power;
    public int accuracy;
    public int type;

    public MissileClass(float speed,int range, double power, int accuracy, int type){
       this.speed = speed;
       this.range = range;
       this.power = power;
       this.accuracy = accuracy;
       this.type = type;
    }
}
