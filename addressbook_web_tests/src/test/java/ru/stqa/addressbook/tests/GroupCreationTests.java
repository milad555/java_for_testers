package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GroupCreationTests extends TestBase {
    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>(List.of());
//        for (var name :List.of("", "group name")){
//            for (var header : List.of("", "group header")){
//                for (var footer : List.of("", "group footer")){
//                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
//                }
//            }
//        }
        var json ="";
        try (var reader = new FileReader("groups.json");
             var breader = new BufferedReader(reader)
        ){
            var line = breader.readLine();
            while (line != null){
                json = json + line;
                line = breader.readLine();
            }
        }

        //var json = Files.readString(Paths.get("groups.json"));
        ObjectMapper mapper = new XmlMapper();

        var value = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>(){});
        result.addAll(value);
        return result;
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "group name'", "", "")));
        return result;
    }
    public static Stream<GroupData> singleRandomGroup() {
        Supplier<GroupData> randomGroup = () -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(20))
                .withFooter(CommonFunctions.randomString(30));
        return Stream.generate(randomGroup).limit(3);
    }
    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroup(GroupData group) {
        var oldGroups = app.jdbc().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.jdbc().getGroupList();
        var extraGroups = newGroups.stream().filter(g -> ! oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups, oldGroups);
    }
}
