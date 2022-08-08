import { Button } from '@rneui/themed'
import React from 'react'
import { useState } from 'react'
import { useEffect } from 'react'
import { Text, View } from 'react-native'
import { Agenda, Calendar, CalendarList } from 'react-native-calendars'
import { getClasInfo } from './util/http'

const TeacherHome = ({navigation}) => {
    const [state, setState] = useState("");

    const x =()=>{
        console.log(state);

    }

    useEffect(()=>{
        async function s(name){
         const c = await getClasInfo(name);
          setState(c)
        }
        s(1);
    
      }, [])
    
  return (
    <View>
        <Button onPress={x()}/>

    {/* <CalendarList markedDates={ {'2022-08-16': {selected: true, marked: true, selectedColor: 'blue'}}}/> */}
   
    {/* <Calendar markedDates={ {'2022-08-16': {selected: true, marked: true, selectedColor: 'blue'}}}/> */}
    </View>

  )
}

export default TeacherHome