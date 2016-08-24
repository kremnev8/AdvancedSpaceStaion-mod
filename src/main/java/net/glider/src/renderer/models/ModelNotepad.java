package net.glider.src.renderer.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNotepad extends ModelBase
{
  //fields
    ModelRenderer notepad;
    ModelRenderer ring1;
    ModelRenderer ring2;
    ModelRenderer ring3;
    ModelRenderer ring4;
    ModelRenderer ring5;
    ModelRenderer pencil;
  
  public ModelNotepad()
  {
    textureWidth = 32;
    textureHeight = 32;
    
      notepad = new ModelRenderer(this, 0, 0);
      notepad.addBox(0F, 0F, 0F, 1, 12, 8);
      notepad.setRotationPoint(7F, 10F, -4F);
      notepad.setTextureSize(32, 32);
      notepad.mirror = true;
      setRotation(notepad, 0F, 0F, 0F);
      ring1 = new ModelRenderer(this, 18, 0);
      ring1.addBox(0F, 0F, 0F, 1, 1, 1);
      ring1.setRotationPoint(7.4F, 9.2F, 2.5F);
      ring1.setTextureSize(32, 32);
      ring1.mirror = true;
      setRotation(ring1, 0F, 0F, 0.5235988F);
      ring2 = new ModelRenderer(this, 18, 0);
      ring2.addBox(0F, 0F, 0F, 1, 1, 1);
      ring2.setRotationPoint(7.4F, 9.2F, 1F);
      ring2.setTextureSize(32, 32);
      ring2.mirror = true;
      setRotation(ring2, 0F, 0F, 0.5235988F);
      ring3 = new ModelRenderer(this, 18, 0);
      ring3.addBox(0F, 0F, 0F, 1, 1, 1);
      ring3.setRotationPoint(7.4F, 9.2F, -0.5F);
      ring3.setTextureSize(32, 32);
      ring3.mirror = true;
      setRotation(ring3, 0F, 0F, 0.5235988F);
      ring4 = new ModelRenderer(this, 18, 0);
      ring4.addBox(0F, 0F, 0F, 1, 1, 1);
      ring4.setRotationPoint(7.4F, 9.2F, -2F);
      ring4.setTextureSize(32, 32);
      ring4.mirror = true;
      setRotation(ring4, 0F, 0F, 0.5235988F);
      ring5 = new ModelRenderer(this, 18, 0);
      ring5.addBox(0F, 0F, 0F, 1, 1, 1);
      ring5.setRotationPoint(7.4F, 9.2F, -3.5F);
      ring5.setTextureSize(32, 32);
      ring5.mirror = true;
      setRotation(ring5, 0F, 0F, 0.5235988F);
      pencil = new ModelRenderer(this, 18, 3);
      pencil.addBox(0F, 0F, 0F, 1, 11, 1);
      pencil.setRotationPoint(7F, 9F, -4.6F);
      pencil.setTextureSize(32, 32);
      pencil.mirror = true;
      setRotation(pencil, 0F, 0.7853982F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    notepad.render(f5);
    ring1.render(f5);
    ring2.render(f5);
    ring3.render(f5);
    ring4.render(f5);
    ring5.render(f5);
    pencil.render(f5);
  }
  public void render()
  {
	  notepad.render(0.0625F);
	  ring1.render(0.0625F);
	  ring2.render(0.0625F);
	  ring3.render(0.0625F);
	  ring4.render(0.0625F);
	  ring5.render(0.0625F);
	  pencil.render(0.0625F);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
  }

}
