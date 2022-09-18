import { View, Text, TouchableOpacity, TextInput, KeyboardAvoidingView } from 'react-native'
import React, { useEffect } from 'react'
import { useState } from 'react';
import { useContext } from 'react';
import { StyleSheet } from 'react-native';
import { AuthContext } from './store/AuthContext';
import { deleteComment, fetchStudentsTeacherComment, getStudentPerSubject, sentComment } from './util/http';

const CommentParent = ({ navigation }) => {
    const [students, setStudents] = useState([])
    const [comment, setComment] = useState([])
    const [comm, setComm] = useState("")
    const authCtx = useContext(AuthContext);


    useEffect(() => {
        async function getStudents(studentid, token) {
            const response = await fetchStudentsTeacherComment(studentid, token)
            setStudents(response.comments)
            console.log(response)
            const comments = []
            for (const i in response.comments) {
                if (response.comments[i].teacher.id == authCtx.userInfo.id &&
                    response.comments[i].role == "ROLE_TEACHER") {
                    const comment = {
                        date: response.comments[i].date,
                        comment: response.comments[i].comment,
                        id: response.comments[i].id,
                        name: response.comments[i].sentBy
                    }
                    comments.push(comment);
                }
            }
            setComment(comments)

        }

        getStudents(authCtx.childId[0], authCtx.token)

        console.log(authCtx.classid)
    }, [])

    async function sendComment(studentid, teacherid, message, token) {

        const date = new Date().toJSON().slice(0, 10).replace(/-/g, '-')
        const messagge = {
            comment: comm,
            date: date.toString(),
            role: authCtx.role,
            sentBy: "Mr " + authCtx.lastname

        }
        const response = await sentComment(authCtx.childId[0], authCtx.userInfo.id, messagge, authCtx.token)
        navigation.pop()

    }

    async function deleteComments(commentid, studentid, token) {
        const response = await deleteComment(commentid, studentid, token);
        navigation.pop()

    }


    return (
        <KeyboardAvoidingView behavior='height' style={styles.container}>
            <View style={styles.tableContainer}>
                <View style={styles.tableRowHeader}>
                    <View style={styles.tableColumnHeader}>
                        <Text style={styles.textHeader}>Comments Received</Text>
                    </View>
                </View>
                <View style={styles.tableRow}>

                    <View style={styles.tableColumnDate}>
                        <Text style={styles.heading}>Date</Text>
                    </View>

                    <View style={styles.tableColumnDate}>
                        <Text style={styles.heading}>Teacher</Text>
                    </View>

                    <View style={styles.tableColumnTotals}>
                        <Text style={styles.heading}>Comment</Text>
                    </View>
                    <View style={styles.tableColumnDate}>
                        <Text style={styles.heading}>Press to delete</Text>
                    </View>
                </View>
                {comment.map(x => {
                    return (
                        <View key={x.id} style={styles.tableRow1}>

                        <View style={styles.tableColumnDate}>
                                <Text style={styles.textLineItem}>{x.date}</Text>
                            </View>

                            <View style={styles.tableColumnDate}>
                                <Text style={styles.textLineItem}>{x.name}</Text>
                            </View>

                            <View style={styles.tableColumnTotals}>
                                <Text style={styles.textLineItem}>{x.comment}</Text>
                            </View>
                            <View style={styles.tableColumnDate}>

                                <TouchableOpacity onPress={() => { deleteComments(x.id, authCtx.childId[0], authCtx.token) }} style={{
                                    alignSelf: 'stretch',
                                    flex: 1,
                                    justifyContent: 'center'

                                }}>
                                    <Text style={{ color: "white", textAlign: 'center', fontSize: 18 }}>
                                        Delete
                                    </Text>
                                </TouchableOpacity>
                            </View>
                        </View>

                    )
                })}
                <View style={styles.tableContainer}>
                    <View style={styles.tableRowHeader}>
                        <View style={styles.tableColumnHeader}>
                            <Text style={styles.textHeader}>Sent comments</Text>
                        </View>
                    </View>
                    <View style={styles.tableRow}>

                        <View style={styles.tableColumnTotals}>
                            <Text style={styles.textLineItem}>Comment</Text>
                        </View>
                    </View>

                    <View style={styles.tableRow}>
                        <View style={styles.textinput}>
                            <TextInput multiline={true} style={styles.input} onChangeText={(y) => { setComm(y) }} />
                        </View>
                    </View>

                    <TouchableOpacity onPress={() => { sendComment() }} style={{
                        backgroundColor: '#608d56',
                        borderRadius: 4, padding: 3, marginTop: 50
                    }}>
                        <Text style={{ color: "white", textAlign: 'center', fontSize: 18 }}>
                            Sent Comment
                        </Text>
                    </TouchableOpacity>


                </View>
            </View>
        </KeyboardAvoidingView>

    )
}
export default CommentParent


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
        flex: 1,
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
    tableRow1: {
        flexDirection: "row",
        marginLeft: 4,
        flex:5,
        maxHeight: 80
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
    input: {
        borderRadius: 2,
        fontSize: 14,
        color: 'black',
        margin: 5,
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
        color: "#FFFFFF",
        textAlign: 'center'
    },
    heading: {
        color: "#FFFFFF",
        textAlign: 'center',
        fontSize: 12
    },
    textinput: {
        alignItems: "center",
        backgroundColor: "white",
        justifyContent: "center",
        margin: 1,
        width: 350,
        height: 70

    }
});