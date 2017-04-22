package edu.tacoma.uw.hindsr.fragmentslab;

import android.support.v4.app.FragmentTransaction;
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
        //Capture the course fragment from the activity layout
        CourseDetailFragment courseDetailFragment = (CourseDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.course_item_frag);
        if(courseDetailFragment != null){
            //if courseDetail frag is available, we're in two=panel layout
            //call a method in the course detail fragment to update its content
            courseDetailFragment.updateCourseItemView(item);
        } else {
            //if the frag is not avialable, we're in the one-panel layout and must swap fragments.
            //create fragment and give it an arguement for the selected student
            //replace whatever is in the fragment_container view with this fragment,
            //and add the transaction to the back stack so teh user can navigate back

            courseDetailFragment = CourseDetailFragment.getCourseDetailFragment(item);

            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, courseDetailFragment)
                    .addToBackStack(null);

            //commit the transaction
            transaction.commit();
        }
    }
}
