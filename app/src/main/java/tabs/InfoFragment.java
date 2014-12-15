package tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.ramap.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joe's Desktop on 12/14/2014.
 */
public class InfoFragment extends Fragment {

    private String[] buildingName = {
            "Jack Coffey Field",
            "Rose Hill Gymnasium",
            "Vincent Lombardi Center",
            "McGinley Center",
            "Hughes Hall",
            "Dealy Hall",
            "Cunniffe House",
            "Loyola Hall",
            "University Church",
            "Queens Court",
            "Loschert Hall",
            "Alumni House",
            "Alumni Court South",
            "Martyrs Court",
            "Campbell Hall",
            "Salice Hall",
            "Conley Hall",
            "Walsh Library",
            "Duane Library",
            "Thebaud Hall",
            "Walsh Hall",
            "John Mulcahy Hall",
            "Tierney Hall",
            "OHare Hall",
            "Keating Hall",
            "Edwards Parade",
            "Freeman Hall",

    };

    private String[] buildingInfo = {
            "Named in honor of John Francis 'Jack' Coffey, the 1910 Fordham graduate considered by many to be the father of Fordham athletics, Jack Coffey Field is the home of the University's football and soccer teams.",
            "When it opened, on January 16, 1925, the 3,470-seat Rose Hill Gymnasium was one of the largest on-campus facilities, earning it the nickname 'The Prairie' due to its large floor space. The oldest Division I facility in the country, the gymnasium has been the site of many legendary college and high school basketball games, including Kareem Abdul Jabbar's final high school game. Today, the men's and women's basketball teams and volleyball squads play here.",
            "Behind the McGinley Center is the Vincent T. Lombardi Center, which is named for Fordham alumnus Vince Lombardi, the Pro Football Hall of Fame coach of the Green Bay Packers and anchor of Fordham's famed Seven Blocks of Granite. The Lombardi Center opened in 1976 and houses a 200-meter indoor running track; six tennis courts; a newly renovated 38-meter, eight-lane swimming pool; competition diving facilities; weight rooms; cardio rooms; five racquetball courts; four squash and handball courts; pole vault and high jump pits, and locker room and a sauna.",
            "Built in 1959, the McGinley Center is Rose Hill's 'living room,' where students socialize, attend events, and dine at the Marketplace and Dagger John's. Named in honor of Father Laurence J. McGinley, president of Fordham University from 1949 to 1963, the student center is also home to the Office of Career Services, the University Bookstore, Campus Ministry, and the Office of Student Leadership and Community Development. It also houses the Dorothy Day Center for Service and Justice, and Global Outreach, a cultural immersion and service program.",
            "Named in honor of Archbishop John Hughes, the first archbishop of New York and founder of Fordham University, Hughes Hall was built in 1891. In 2012 it underwent a complete renovation and became the new home of the Gabelli School of Business, following a $25 million gift to Fordham from alumnus Mario J. Gabelli. In recognition of his gift, Fordham renamed the undergraduate College of Business Administration the Gabelli School of Business. The 19th-century building features 21st-century technology, including a media and technology center with mock trading floor, a boardroom, six conference rooms, computer labs, and eco-friendly amenities.",
            "Built in 1867, Dealy Hall is named in honor of Father Patrick F. Dealy, president of the University from 1882 to 1885, who ushered in a significant period of expansion for the University. Renovated on four different occasions, Dealy Hall is currently the home of numerous academic departments, including African and African American studies, economics, English, history, international political economy, Latin American and Latino studies, sociology and anthropology, psychology, and urban studies.",
            "Built in 1838, the Cunniffe House is a New York State registered landmark. Once known as the Administration Building, it was renamed in 2013 in honor of alumnus and trustee emeritus Maurice J. Cunniffe. In addition to the Office of the President, this building includes several vice presidents' offices, the Gallery of Presidents featuring portraits, and the University's Hall of Honor, which recognizes alumni whose lives have exemplified and brought recognition to the ideals to which the University is devoted. Outside the Cunniffe House, between Hughes Hall and Dealy Hall, is the Carolyn Dursi Cunniffe Fountain, named after Maurice Cunniffe's wife, Carolyn Dursi Cunniffe.",
            "Built in 1928, Loyola is named after the founder of the Jesuits, St. Ignatius Loyola. Loyola Hall houses approximately 110 freshmen.",
            "Built in 1845, the University Church is the center of spiritual life at Rose Hill. Throughout its rich history, the University Church has undergone several transformations. The exterior was remodeled and expanded in 1929, and the interior of the church was renovated in 1990. The most extensive restoration, however, took place in 2003 and 2004. It was declared an official New York City landmark in 1970, as one of the finest examples of Gothic architecture in the city.",
            "Built at the same time as the University Church, Queen's Court is home to a tightly knit community of 150 students. The residential college was established to assist in the integration of first-year students into the University and New York City communities. Its inception as a residence hall in 1987 came in response to a need for a first-year residence that would embody the best qualities of a Jesuit education: a devotion to learning, a respect for tradition and a commitment to community living. All three hallsâ€”St. John's, St. Robert's, and Bishop'sâ€”are co-ed, with separate wings for male and female students.",
            "Formerly known as Alumni Court North, Loschert Hall is an elevator-equipped, co-ed residence hall accommodating approximately 250 students, with separate wings for male and female students. In 2009, the hall was renamed in honor of Fordham benefactor William J. Loschert, a 1961 alumnus of the Gabelli School of Business.",
            "Alumni House was built in 1840 by William Rodrigue (pronounced Rod-'reeg), the brother-in-law of Fordham's founder, Archbishop John Hughes. It is home to the student-run Rodrigue's Coffee House, a popular late-night student hangout.",
            "Opened in 1987, Alumni Court South is an elevator-equipped residence hall accommodating approximately 250 students. It is co-ed, with separate wings for male and female students.",
            "Built in 1951, Martyrs' Court is made up of three sections named after the Jesuit martyrs Isaac Jogues (pronounced Joegs), John LaLande, and Rene Goupil (pronounced Goo-'peel). Students are housed in double bedrooms, with a number of single bedrooms. Located throughout the hall are 15 community lounges with kitchenettes and 17 quiet study lounges. A large common lounge is located in LaLande Hall.",
            "Campbell, Salice, and Conley Residences Halls, which opened in August 2010, are the newest structural additions to the Rose Hill campus and immediately fostered a community of intellectual, social, and spiritual developmentâ€”the centerpiece of a Fordham education. The construction of the halls was made possible thanks to the generous support of Fordham alumni Robert E. Campbell and his wife, Joan M. Campbell, and Thomas P. Salice and his wife, Susan Conley Salice. The halls house approximately 460 undergraduate students.",
            "Campbell, Salice, and Conley Residences Halls, which opened in August 2010, are the newest structural additions to the Rose Hill campus and immediately fostered a community of intellectual, social, and spiritual developmentâ€”the centerpiece of a Fordham education. The construction of the halls was made possible thanks to the generous support of Fordham alumni Robert E. Campbell and his wife, Joan M. Campbell, and Thomas P. Salice and his wife, Susan Conley Salice. The halls house approximately 460 undergraduate students.",
            "Campbell, Salice, and Conley Residences Halls, which opened in August 2010, are the newest structural additions to the Rose Hill campus and immediately fostered a community of intellectual, social, and spiritual developmentâ€”the centerpiece of a Fordham education. The construction of the halls was made possible thanks to the generous support of Fordham alumni Robert E. Campbell and his wife, Joan M. Campbell, and Thomas P. Salice and his wife, Susan Conley Salice. The halls house approximately 460 undergraduate students.",
            "Opened in 1997, the William D. Walsh Family Library is named in honor of the late Fordham alumnus and benefactor William D. Walsh and his late wife, Jane. One of the largest university libraries in North America, Walsh Library houses more than 2 million volumes. It is also home to the Fordham Museum of Greek, Etruscan and Roman Art, a 4,000-square-foot space featuring more than 260 antiquities dating from the seventh century B.C. through the third century A.D. The collection was donated to the University by William D. Walsh and his wife.",
            "For more than 70 years, Duane Library served as the architectural and intellectual centerpiece of the Rose Hill campus until it was temporarily closed following the opening of the William D. Walsh Family Library in 1997. After undergoing a three-year, $12 million renovation, Duane Library is home to the Department of Theology, the Office of Undergraduate Admission, the Francis and Ann Curran Center for American Catholic Studies, Butler Commons, and Tognino Hall, which is named in honor of chairman of the Fordham University Board of Trustees John N. Tognino, a 1975 alumnus of Fordham School of Professional and Continuing Studies, and his wife, Norma.",
            "Built in 1886, Thebaud Hall is named for Augustus Thebaud, S.J., Fordham University's first Jesuit president. Originally the University's science building, Thebaud Hall is currently the home of the Offices of Enrollment Services, Academic Records, Financial Services, and Student Accounts. The University's Department of Public Safety is also located here.",
            "Once known as '555' for its original address on 191st Street, Walsh Hall is a 13-story residence hall for 492 upperclass students, primarily juniors and seniors. Named after Fordham President Father Michael P. Walsh, who oversaw University operations from 1969 to 1972, Walsh Hall is the tallest residence hall on campus.",
            "Built in 1968, John Mulcahy Hall is a fully air-conditioned modern classroom and laboratory facility. It is home to the University's chemistry, mathematics, and computer and information science departments, as well as Rose Hill's microcomputer lab.",
            "Built in celebration of the University's 150th anniversary, Tierney Hall was once known as Sesquicentennial Hall. University officials renamed the residence hall in memory of Bill Tierney, a popular student and football player who died tragically on Jack Coffey Field prior to the Rams' 1996 Homecoming game against Lafayette. Tierney Hall is Rose Hill's only residential college occupied by both upperclass students and freshmen.",
            "Combining Gothic architecture and modern amenities, O'Hare Hall opened in August 2000. The residence hall is named after Father Joseph A. O'Hare, president of Fordham University from 1984 to 2003. The air-conditioned, three-wing, five-level complex consists of 13 neighborhoods and houses 550 students.",
            "With its great central clock tower, Keating Hall has been Fordham's signature building for the better part of eight decades. Keating serves as the backdrop for the University's annual commencement ceremony. The University president and many faculty members and administrators preside over the ceremony from the terrace, also known as the Terrace of Presidents. The granite steps between Edwards Parade and Keating Hall are engraved with the names of heads of state who have received honorary doctorates from Fordham, including former U.S. presidents Franklin Delano Roosevelt and Harry S. Truman. Keating is also the home of WFUV (90.7 FM), the University's award-winning, non-commercial, listener-supported public radio station.",
            "The great lawn, affectionately known as 'Eddies,' is the site of the University's commencement ceremony each spring. It is named in honor of General Clarence R. Edwards, a professor of military science and tactics at Fordham in the late 19th Century.",
            "Built in 1930, Freeman Hall is an academic building with both classrooms and research laboratories for the sciences. Freeman is home to the Departments of Physics and the University's 3-2 Cooperative Program in Engineering, through which students earn a bachelor's degree from Fordham in three years and a bachelor of science degree in engineering in two years at either Columbia University or Case Western Reserve University. It also features an alternative energy lab, which allows students in the University's Engineering Physics Program to perform experiments in energy conversion and storage. Power for the lab is generated by solar panels and wind turbines, which are mounted on the roof of the hall."
    };

    int[] icon = new int[]{
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house,
            R.drawable.ic_house
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<27;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("buildingName", /*"Info: " +*/  buildingInfo[i]);
            hm.put("buildingInfo", /*"Building : " +*/ buildingName[i]);
            hm.put("icon", Integer.toString(icon[i]) );
            aList.add(hm);
        }

        String[] from = { "icon","buildingName","buildingInfo" };

        int[] to = { R.id.icon,R.id.buildingInfo,R.id.buildingName,R.id.textView};

        View v = inflater.inflate(R.layout.fragment_top_rated, container,false);
        ListView list = (ListView)v.findViewById(R.id.listView1);
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.listview_layout, from, to);
        list.setAdapter(adapter);
        return v;
    }
        //This is old
        /*View rootView = inflater.inflate(R.layout.fragment_info, container, false);

        return rootView;*/
    }

