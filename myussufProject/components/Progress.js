import { StyleSheet, Text, View } from 'react-native'
import React, { useContext, useEffect, useState } from 'react'
import { AuthContext } from './store/AuthContext'
import { getAttendancePerStudent, getClassPerSubject } from './util/http'

const Progress = (navigation) => {
    const [attendance, setAttendance] = useState([])
    const [classes, setClasses] = useState([]);
    const authCtx = useContext(AuthContext)

    useEffect(() => {
        async function getClass(studentid, subjectid) {
            const response = await getClassPerSubject(subjectid)
            const rep = await getAttendancePerStudent(studentid, subjectid);
            const x = []
            for (const i in response) {
                const m = false;
                const lesson = {
                    id: response[i].id,
                    dow: response[i].dow,
                    time: response[i].time,
                    topic: response[i].topic,
                    attendance: m
                }
                x.push(lesson)
            }
            setClasses(x)
            setAttendance(rep)

        }
        getClass(authCtx.childId[0], authCtx.childId[1]);
    }, [])

    return (

        <View style={styles.container}>


      <View style={styles.tableContainer}>
        <View style={styles.tableRowHeader}>
          <View style={styles.tableColumnHeader}>
            <Text style={styles.textHeader}>Progress</Text>
          </View>
        </View>
        <View style={styles.tableRow}>
          <View style={styles.tableColumnDate}>
            <Text style={styles.textLineItem}>Topic</Text>
          </View>
          <View style={styles.tableColumnTotals}>
            <Text style={styles.textLineItem}>Comment</Text>
          </View>
        </View>
        {classes.map(x => {
                    return (
                        <View key={x.id} style={styles.tableRow}>
                            <View style={styles.tableColumnDate}>
                                <Text style={styles.textLineItem}>{x.topic}</Text>
                            </View>
                           
                            <View style={styles.tableColumnTotals}>

                            {attendance.map(y => {
                                if (y.register.id === x.id) {
                                    return (
                                            <Text key={y.id} style={styles.textLineItem}>{y.understanding}</Text>
                                    )
                                }
                               
                            })}
                            </View>

                        </View>

                    )
                })}



      </View>
    </View>
  )
}

export default Progress

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