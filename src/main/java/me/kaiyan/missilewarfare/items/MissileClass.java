package me.kaiyan.missilewarfare.items;

public class MissileClass {
    public double speed;
    public int range;
    public double power;
    public int accuracy;
    public int type;

    public MissileClass(double speed,int range, double power, int accuracy, int type){
       this.speed = speed;
       this.range = range*range;
       this.power = power;
       this.accuracy = accuracy;
       this.type = type;
    }
}
