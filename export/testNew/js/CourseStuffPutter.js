function initStuff(view){
    var dataFile = "./js/CourseStuff.json";
    loadStuff(dataFile, view);
}
function loadStuff(jsonFile,view) {
    $.getJSON(jsonFile, function (json) {
        initBanner(json);
        initNavbar(json,view);
    });
}

function initBanner(data){
    var banner = $("#banner");
    banner.append(data.course_name + " " + data.course_num + "-" + data.semester + " " + data.year + "<br/>" + data.course_title);
}

function initNavbar(data,view){
    var navbar = $("#navbar");
    for(var i = 0; i < data.site_pages.length; i+=1){
        if(data.site_pages[i] == view+".html"){
            navbar.append("<a id=\"view_link\" class=\"open_nav\" href=\"" + view + ".html\">"+cap(view)+"</a>");
            continue;
        }
        if(data.site_pages[i] == "main.html"){
            navbar.append("<a id=\"main_link\" class=\"nav\" href=\"main.html\">Main</a>");
        }
        else if(data.site_pages[i] == "schedule.html"){
            navbar.append("<a id=\"schedule_link\" class=\"nav\" href=\"schedule.html\">Schedule</a>");
        }
        else if(data.site_pages[i] == "projects.html"){
            navbar.append("<a id=\"projects_link\" class=\"nav\" href=\"projects.html\">Projects</a>");
        }
        else if(data.site_pages[i] == "hws.html"){
            navbar.append("<a id=\"hws_link\" class=\"nav\" href=\"hws.html\">Hws</a>");
        }
        else if(data.site_pages[i] == "syllabus.html"){
            navbar.append("<a id=\"syllabus_link\" class=\"nav\" href=\"syllabus.html\">Syllabus</a>");
        }
    }   
}
function cap(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}