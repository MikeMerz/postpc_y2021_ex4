package exercise.find.roots;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CalculateRootsService extends IntentService {


  public CalculateRootsService() {
    super("CalculateRootsService");
  }
  private  long DEFUALT =1;

  @Override
  protected void onHandleIntent(Intent intent) {
    if (intent == null) return;
    long timeStartMs = System.currentTimeMillis();
    long numberToCalculateRootsFor = intent.getLongExtra("number_for_service", 0);
    if (numberToCalculateRootsFor <= 0) {
      Log.e("CalculateRootsService", "can't calculate roots for non-positive input" + numberToCalculateRootsFor);
      return;
    }
    /*
    TODO:
     calculate the roots.
     check the time (using `System.currentTimeMillis()`) and stop calculations if can't find an answer after 20 seconds
     upon success (found a root, or found that the input number is prime):
      send broadcast with action "found_roots" and with extras:
       - "original_number"(long)
       - "root1"(long)
       - "root2"(long)
     upon failure (giving up after 20 seconds without an answer):
      send broadcast with action "stopped_calculations" and with extras:
       - "original_number"(long)
       - "time_until_give_up_seconds"(long) the time we tried calculating

      examples:
       for input "33", roots are (3, 11)
       for input "30", roots can be (3, 10) or (2, 15) or other options
       for input "17", roots are (17, 1)
       for input "829851628752296034247307144300617649465159", after 20 seconds give up

     */
    long i=2;
    Intent newIntent = new Intent();
    long  diff = System.currentTimeMillis() - timeStartMs;
    while(i<numberToCalculateRootsFor)
    {
      if(diff>20000)
      {
        newIntent.putExtra("original_number",numberToCalculateRootsFor);
        newIntent.putExtra("time_until_give_up_seconds",diff/1000);
        newIntent.setAction("stopped_calculations");
        sendBroadcast(newIntent);
        return;
      }
      if(numberToCalculateRootsFor%i==0)
      {
        newIntent.putExtra("original_number",numberToCalculateRootsFor);
        newIntent.putExtra("root1",i);
        newIntent.putExtra("root2",numberToCalculateRootsFor/i);
        newIntent.putExtra("time_spent",diff/1000);
        newIntent.setAction("found_roots");
        sendBroadcast(newIntent);
        return;
      }
      diff = System.currentTimeMillis() - timeStartMs;
      System.out.println(diff);
      ++i;
    }
    System.out.println("IM HERE??");
    newIntent.putExtra("original_number",numberToCalculateRootsFor);
    newIntent.putExtra("root2",DEFUALT);
    newIntent.putExtra("root1",numberToCalculateRootsFor);
    newIntent.putExtra("time_spent",diff/1000);
    newIntent.setAction("found_roots");
    sendBroadcast(newIntent);


  }
}