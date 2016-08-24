package net.glider.src.entity.choreo;

import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.Entity;

public abstract class ChoreoScene {
	
	public Entity entity;
	
	public boolean choreoStarted = false;
	
//	private HashMap<String, ChoreoScene> registeredChoreo =new HashMap();
	
	public ChoreoScene(Entity actingEntity)
	{
		this.entity = actingEntity;
	}

	
	public void StartChoreo()
	{
		this.choreoStarted = true;
	}
	
	public void CancelChoreo()
	{
		this.choreoStarted = false;
	}
	
	public abstract void TickChoreo(int nowtime);
	
	public abstract List<Integer> GetChoreoMoments();
	
	public abstract void UpdateChoreoEntity();
	
	public abstract String GetChoreoName();

}
