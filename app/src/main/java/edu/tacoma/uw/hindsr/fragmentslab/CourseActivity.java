package edu.tacoma.uw.hindsr.fragmentslab;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
            //and add the transaction to the back stack so the user can navigate back

            courseDetailFragment = CourseDetailFragment.getCourseDetailFragment(item);

            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, courseDetailFragment)
                    .addToBackStack(null);

            //commit the transaction
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_about:
                AboutFragment aboutFragment = (AboutFragment)
                        getSupportFragmentManager().findFragmentById(R.id.action_about);
                CourseDetailFragment courseDetailFragment = (CourseDetailFragment)
                        getSupportFragmentManager().findFragmentById(R.id.course_item_frag);
                if(courseDetailFragment != null){
                    Intent intent = new Intent(this, AboutActivity.class);
                    this.startActivity(intent);
                }else{
                    aboutFragment = AboutFragment.getAboutFragment();
                    FragmentTransaction transaction = getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, aboutFragment)
                            .addToBackStack(null);
                    transaction.commit();
                }
                    //commit the transaction
                    //transaction.commit();

                break;

            default:
                break;
        }

        return true;
    }


}
