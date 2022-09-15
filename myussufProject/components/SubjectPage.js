import { View, Text, StyleSheet } from 'react-native'
import React from 'react'
import { useContext } from 'react';
import { AuthContext } from './store/AuthContext';
import { useState } from 'react';
import { useEffect } from 'react';
import { getClassPerSubject } from './util/http';
import { useNavigationState } from '@react-navigation/native';

const SubjectPage = ({ navigation, props }) => {
  const [timetable, setTimetable] = useState([])
  const [childId, setChilId] = useState({})
  const authCtx = useContext(AuthContext)

  useEffect(() => {
    async function getClass(subjectid) {
      const response = await getClassPerSubject(subjectid)
      console.log(response, "ASSSSSSSSSSSSSSSSSSSSSSs")
      const x = []
      for (const i in response) {
        const lesson = {
          dow: response[i].dow,
          time: response[i].time,
          topic: response[i].topic
        }
        x.push(lesson)
      }
      setTimetable(x)

    }
    getClass(authCtx.subjectid)

    console.log(authCtx.childId)

  }, [])

  return (
    <View style={styles.container}>


      <View style={styles.tableContainer}>
        <View style={styles.tableRowHeader}>
          <View style={styles.tableColumnHeader}>
            <Text style={styles.textHeader}>Timetable</Text>
          </View>
        </View>
        <View style={styles.tableRow}>
          <View style={styles.tableColumnDate}>
            <Text style={styles.textLineItem}>Date</Text>
          </View>
          <View style={styles.tableColumnDate}>
            <Text style={styles.textLineItem}>Time</Text>
          </View>
          <View style={styles.tableColumnTotals}>
            <Text style={styles.textLineItem}>Topic</Text>
          </View>
        </View>

        {timetable.map(x => {
          return (
            <View style={styles.tableRow}>
              <View style={styles.tableColumnDate}>
                <Text style={styles.textLineItem}>{x.dow}</Text>
              </View>
              <View style={styles.tableColumnDate}>
                <Text style={styles.textLineItem}>{x.time}</Text>
              </View>
              <View style={styles.tableColumnTotals}>
                <Text style={styles.textLineItem}>{x.topic}</Text>
              </View>
            </View>

          )
        })}









      </View>
    </View>
  );

}

export default SubjectPage

const styles = StyleSheet.create({
  container: {
    backgroundColor: "#000000",
    flex: 1,
    padding: 10
  },
  tableColumnHeader: {
    alignItems: "center",
    backgroundColor: "#1FE0A2",
    flex: 5,
    justifyContent: "center"
  },
  tableColumnDate: {
    alignItems: "center",
    backgroundColor: "#000000",
    flex: 3,
    justifyContent: "center",
    margin: 1
  },
  tableColumnTotals: {
    alignItems: "center",
    backgroundColor: "#000000",
    flex: 2,
    justifyContent: "center",
    margin: 1
  },
  tableRow: {
    flex: 5,
    flexDirection: "row",
    maxHeight: 30
  },
  tableRowHeader: {
    flex: 5,
    flexDirection: "row",
    maxHeight: 40
  },
  tableContainer: {
    backgroundColor: "#202020",
    borderRadius: 5,
    flex: 1,
    marginTop: 0,
    padding: 10
  },
  textHeader: {
    color: "#000000",
    fontWeight: "bold"
  },
  textHeaderSubTitle: {
    color: "#000000",
    fontSize: 12
  },
  textLineItem: {
    color: "#FFFFFF"
  }
});