function initStuff(){
    var dataFile = "./js/CourseStuff.json";
    loadStuff(dataFile);
}
function loadStuff(jsonFile) {
    $.getJSON(jsonFile, function (json) {
        initBanner(json);
    });
}

function initBanner(data){
    var banner = $("#banner");
    banner.append(data.course_name + " " + data.course_num + " " + data.course_title);
}
