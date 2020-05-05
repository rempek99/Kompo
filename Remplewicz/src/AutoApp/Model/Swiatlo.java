package AutoApp.Model;

import java.awt.*;

public class Swiatlo implements Obsluga_Swiatla{
    boolean wlaczone;
    Color barwa;
    public Color getBarwa() {
        return barwa;
    }

    public void setBarwa(Color barwa) {
        this.barwa = barwa;
    }
    @Override
    public void wylacz() {
        this.wlaczone = false;
        System.out.println("**Wylaczone**");
    }

    @Override
    public void wlacz() {
        this.wlaczone = true;
        System.out.println("*Wlaczone*");
    }

    @Override
    public void migaj() {
        for(int i=0;i<4;i++)
        {
            if(i%2 ==0)
                wlacz();
            else
                wylacz();
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}