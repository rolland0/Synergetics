package mod.rolland0.scraps;

import net.minecraftforge.event.Event;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.IEventListener;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;

public class SynOreReplacer implements IEventListener {

	@Override
	@ForgeSubscribe
	public void invoke(Event event) {
		if(event instanceof GenerateMinable) {
			GenerateMinable minable = (GenerateMinable)event;
			replace(event, minable.type);
		}
	}
	
	public boolean replace(Event event, EventType oreType) {
		boolean shouldReplace = false;
		switch(oreType) {
		case COAL:
			//System.out.println("Coal");
			shouldReplace = true;
			break;
		case DIAMOND:
			//System.out.println("Diamond");
			shouldReplace = true;
			break;
		case GOLD:
			//System.out.println("Gold");
			shouldReplace = true;
			break;
		case IRON:
			//System.out.println("Iron");
			shouldReplace = true;
			break;
		case LAPIS:
			//System.out.println("Lapis");
			shouldReplace = true;
			break;
		case REDSTONE:
			//System.out.println("Redstone");
			shouldReplace = true;
			break;
		default:
			//System.out.println("Other");
			break;
		}
		if(shouldReplace) {
			event.setResult(Result.DENY);
		}
		return shouldReplace;
	}
	
//	@Override
//	@ForgeSubscribe
//	public void invoke(Event event) {
//		if(event instanceof OreGenEvent) {
//			OreGenEvent oreGenEvent = (OreGenEvent)event;
//			try {
//				Field minable = oreGenEvent.getClass().getField("GenerateMinable");
//				EventType oreType = (EventType)minable.get(oreGenEvent);
//				System.out.println(oreType);
//			} catch (NoSuchFieldException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalArgumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			event.setResult(Event.Result.DENY);
//		}	
//	}
}
