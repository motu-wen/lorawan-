$(function getLouId() {
      // body...
      teachingbuilding=$("#teachingbuilding");
      var i=1;
      for (var i = 1; i <=12; i++) {
        var option="<option id='"+i+"' data-icon='glyphicon glyphicon-home' value='"+i+"'>第"+i+"教学楼</option>";
        teachingbuilding.append(option);
        teachingbuilding.selectpicker('refresh');
      }
      
    })
function selectedtchbd() {
  floor=$("#floor");
  for (var i =  1; i <= 6; i++) {
    var option="<option id='"+i+"' value='"+i+"'>"+i+"楼</option>";
    floor.append(option);
    floor.selectpicker('refresh');
  }
}
function selectedfloor() {
  room=$("#room");
  var n=floor.val();
  for (var i =1; i <= 8; i++) {
    var option="<option id='"+i+"' value='"+i+"'>"+n+"0"+i+"</option>";
    room.append(option);
    room.selectpicker('refresh');
  }
}
function getStudent() {
  var classroom={
    'build':teachingbuilding.val(),
      'floor':floor.val(),
      'room':room.val(),
      'count':0
  };
  $.ajax({
    url:"/getStudentCount",
    data:JSON.stringify(classroom),
    type:"post",
    contentType: "application/json; charset=utf-8",
    success:function (response) {
      var count=$("#getcount");
      count.append("<h3>"+response.count+"</h3>");

      // alert(item.count);
    }
  });
}