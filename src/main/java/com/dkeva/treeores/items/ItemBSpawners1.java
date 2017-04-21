package com.dkeva.treeores.items;

import com.dkeva.treeores.entities.EntityCoalBoss;
import com.dkeva.treeores.entities.EntityGoldBoss;
import com.dkeva.treeores.entities.EntityIronBoss;
import com.dkeva.treeores.entities.EntityRedstoneBoss;
import com.dkeva.treeores.enums.TV1;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by nefelibata on 4/18/17.
 */
public class ItemBSpawners1 extends Item {
    public ItemBSpawners1() {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack stack) {
        int i = stack.getMetadata();
        return super.getUnlocalizedName() + "_" + TV1.byMetadata(i).getUnlocalizedName();
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < 4; ++i) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }


    public ActionResult<ItemStack> onItemRightClick(ItemStack ie, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if (!playerIn.capabilities.isCreativeMode) {
            --ie.stackSize;
        }
        if (!worldIn.isRemote) {
            Vec3d lookVec = playerIn.getLookVec();
            double posX = playerIn.posX + (lookVec.xCoord * 6D);
            double posY = playerIn.posY + 2D;
            double posZ = playerIn.posZ + (lookVec.zCoord * 6D);
            switch (ie.getMetadata()) {
                case 0:

                    EntityIronBoss entity1 = new EntityIronBoss(worldIn);
                    entity1.setPosition(posX, posY, posZ);
                    worldIn.spawnEntityInWorld(entity1);
                    break;
                case 1:
                    EntityGoldBoss entity2 = new EntityGoldBoss(worldIn);
                    entity2.setPosition(posX, posY, posZ);
                    worldIn.spawnEntityInWorld(entity2);
                    break;
                case 2:
                    EntityCoalBoss entity3 = new EntityCoalBoss(worldIn);
                    entity3.setPosition(posX, posY, posZ);
                    worldIn.spawnEntityInWorld(entity3);
                    break;
                case 3:
                    EntityRedstoneBoss entity4 = new EntityRedstoneBoss(worldIn);
                    entity4.setPosition(posX, posY, posZ);
                    worldIn.spawnEntityInWorld(entity4);
                    break;
            }
            return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));
        }
        return new ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(hand));    }
}
