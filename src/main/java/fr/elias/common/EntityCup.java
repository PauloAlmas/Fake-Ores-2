package fr.elias.common;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCup extends EntityCreature {

	public EntityCup(World worldIn)
	{
		super(worldIn);
		setSize(0.7F, 0.8F);
	}

	public void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
	}
	public void onLivingUpdate()
	{
		this.rotationYaw = this.rotationYawHead = 0.0F;
        this.motionX *= 0.0d;
        this.motionY *= 0.0d;
        this.motionZ *= 0.0d;
		super.onLivingUpdate();
	}
	public boolean isMovementBlocked()
	{
		return true;
	}
	public boolean attackEntityFrom(DamageSource damagesource, float f)
	{
		if(damagesource.getEntity() instanceof EntityPlayer)
		{
			((EntityPlayer)damagesource.getEntity()).triggerAchievement(FakeOres.broken_Cup);
			if(!worldObj.isRemote)
			{
				this.dropItem(Items.apple, rand.nextInt(10));
				this.dropItem(Items.gold_ingot, rand.nextInt(10));
				this.dropItem(Items.diamond, rand.nextInt(10));
				this.dropItem(Items.emerald, rand.nextInt(10));
				this.dropItem(Items.iron_ingot, rand.nextInt(10));
				this.dropItem(Items.coal, rand.nextInt(10));
				this.dropItem(Items.quartz, rand.nextInt(10));
			}
			this.spawnExplosionParticle();
			this.setDead();
			this.worldObj.playSoundAtEntity(this, "random.anvil_land", 1.0F, 1.0F);
			return true;
		}else{
			return false;
		}
	}
}
