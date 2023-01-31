/*
 *  Document   : compCalendar.js
 *  Author     : pixelcave
 *  Description: Custom javascript code used in Calendar page
 */
var CompCalendar = function() {
    var calendarEvents  = $('.calendar-events');

    /* Function for initializing drag and drop event functionality */
    var initEvents = function() {
        calendarEvents.find('li').each(function() {
            // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
            var eventObject = { title: $.trim($(this).text()), color: $(this).css('background-color') };

            // store the Event Object in the DOM element so we can get to it later
            $(this).data('eventObject', eventObject);

            // make the event draggable using jQuery UI
            $(this).draggable({ zIndex: 999, revert: true, revertDuration: 0 });
        });
    };

    return {
        init: function(data) {
            /* Initialize drag and drop event functionality */
            initEvents();

            $('#calendar').ready(function (data) {
                /* Initialize FullCalendar */
                var date = new Date();
                var d = date.getDate();


                $.getJSON('/getEvents',function (data) {
                  var events = []; //The array
                  for(var i =0; i < data.length; i++)
                  {events.push( {id:data[i].id,
                      title: data[i].event , start: data[i].tripDate})}

                $('#calendar').fullCalendar({
                    header: {
                        left: 'title',
                        center: '',
                        right: 'today month,agendaWeek,agendaDay prev,next'
                    },
                    firstDay: 1,
                    editable: true,
                    droppable: true,
                    selectable: true,
                    selectHelper: true,
                    select: function (start, end, allDay) {
                        var title = prompt('Event Title:');
                        if (title) {
                            $.ajax({
                                type : "POST",
                                url : "/api/addEvent/",
                                data :{
                                    "title" : title,
                                    "startDate" : start.toString()

                                },
                                success: function () {
                                    window.location.reload();
                                },
                                error: function (e) {
                                    console.log(e);
                                }
                            });
                        }

                    },
                    eventClick: function (calEvent, jsEvent, view) {
                        /**
                         * calEvent is the event object, so you can access it's properties
                         */

                        if (confirm("Really delete event " + calEvent.title + " " + calEvent.id + " ?")) {
                            // delete event in backend
                            $.ajax({
                                type : "DELETE",
                                url : "/api/deleteEvent/" + calEvent._id,
                                success: function () {
                                    $('#calendar').fullCalendar('removeEvents', calEvent._id);

                                },
                                error: function (e) {
                                    console.log(e);
                                }
                            });


                        }
                    },
                    eventLimit:true,
                    events:events

                });
            });
              });
        }
    };
}();