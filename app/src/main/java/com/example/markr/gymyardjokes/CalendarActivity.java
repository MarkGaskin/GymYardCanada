package com.example.markr.gymyardjokes;

import android.app.ActionBar;
import android.app.usage.UsageEvents;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static java.util.logging.Logger.global;

public class CalendarActivity extends AppCompatActivity {

    Date m_selected_date;
    CompactCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        final SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());

        calendarView = findViewById(R.id.compactcalendar_view);

        final TextView textView = (TextView) findViewById(R.id.monthYearTextView);
        final Date currentDay = new Date();
        textView.setText(dateFormatForMonth.format(currentDay));
        calendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        calendarView.displayOtherMonthDays(true);
        calendarView.setUseThreeLetterAbbreviation(false);
        calendarView.setEventIndicatorStyle(CompactCalendarView.FILL_LARGE_INDICATOR);

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked){
                if(!currentDay.before(dateClicked))
                {
                    showFirstPopup();
                    m_selected_date = dateClicked;
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                textView.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });


    }

    public void store_result()
    {
        int m_result_color = Color.parseColor(increment_to_color(m_increment));
        Event event = new Event(m_result_color, m_selected_date.getTime());
        calendarView.addEvent(event);
        calendarView.
    }

    public String increment_to_color(int increment)
    {
        switch(increment)
        {
            case 1: return this.getString(R.string.WorkedOutColor);
            case 2: return this.getString(R.string.RestDayColor);
            case 3: return this.getString(R.string.InspoDayColor);
            case 4: return this.getString(R.string.OofDayColor);
            default: return "";
        }
    }

    int m_increment;

    public void showFirstPopup()
    {
        m_increment = 0;
        showNextPopup();
    }

    public void showNextPopup()
    {
        m_increment++;
        switch (m_increment)
        {
            case 1:
                showPopup("Did you work out today?");
                break;

            case 2:
                showPopup("Was it a God-given rest day?");
                break;

            case 3:
                showPopup("Are you at least inspired to workout tomorrow?");
                break;

            case 4:
                showPopup("Oof.");
                break;

            default:
                Log.d("d","Unknown m_increment");
                break;
        }
    }

    boolean return_value = false;

    public boolean showPopup(String in_string){


        CustomDialogClass cdd = new CustomDialogClass(this, in_string);
        cdd.setMyDialogListener( new CustomDialogClass.MyDialogListener()
        {
            public void userAccepted () {
                return_value = true;
                store_result();
            }
            public void userDeclined() {
                return_value = false;
                if (m_increment < 4) {
                    showNextPopup();
                }
                else
                {
                    store_result();
                    m_increment = 0;
                }
            }
        });
        cdd.show();
        cdd.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        return return_value;
    }
}

