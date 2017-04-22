package edu.tacoma.uw.hindsr.fragmentslab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.tacoma.uw.hindsr.R;
import edu.tacoma.uw.hindsr.fragmentslab.CourseFragment;
import edu.tacoma.uw.hindsr.fragmentslab.course.CourseContent;

public class CourseActivity extends AppCompatActivity implements CourseFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        if (findViewById(R.id.fragment_container)!= null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new CourseFragment())
                    .commit();
        }

    }

    @Override
    public void onListFragmentInteraction(CourseContent.CourseItem item) {

    }
}
