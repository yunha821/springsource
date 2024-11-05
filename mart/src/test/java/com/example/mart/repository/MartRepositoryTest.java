package com.example.mart.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entitiy.constant.DeliveryStatus;
import com.example.mart.entitiy.constant.OrderStatus;
import com.example.mart.entitiy.item.Delivery;
import com.example.mart.entitiy.item.Item;
import com.example.mart.entitiy.item.Member;
import com.example.mart.entitiy.item.Order;
import com.example.mart.entitiy.item.OrderItem;
import com.example.mart.repository.item.DeliveryRepository;
import com.example.mart.repository.item.ItemRepository;
import com.example.mart.repository.item.MemberRepository;
import com.example.mart.repository.item.OrderItemRepository;
import com.example.mart.repository.item.OrderRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MartRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    // C
    @Test
    public void memberInsertTest() {

        memberRepository.save(Member.builder().name("user1").city("서울시").street("123-456").zipcode("123565").build());
        memberRepository.save(Member.builder().name("user2").city("부산시").street("123-456").zipcode("456789").build());
        memberRepository.save(Member.builder().name("user3").city("경기도").street("123-456").zipcode("789456").build());
    }

    @Test
    public void itemInsertTest() {
        itemRepository.save(Item.builder().name("tshirt").price(25300).quantity(1).build());
        itemRepository.save(Item.builder().name("jeans").price(88550).quantity(3).build());
        itemRepository.save(Item.builder().name("jacket").price(45612).quantity(2).build());

    }

    @Test
    public void orderInsertTest() {

        Member member = memberRepository.findById(1L).get();
        Item item = itemRepository.findById(2L).get();

        Order oder = Order.builder()
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.ORDER)
                .member(member)
                .build();
        orderRepository.save(oder);

        Order order = orderRepository.findById(2L).get();

        OrderItem orderItem = OrderItem.builder()
                .price(200000)
                .count(2)
                .order(order)
                .item(item)
                .build();
        orderItemRepository.save(orderItem);

        // item 수량 감소

    }

    // R
    @Test
    public void memberAndItemAndOrderListTest() {

        // 주문 내역 조회
        // orderRepository.findAll().forEach(order -> System.out.println(order));
        // Order(id=1, orderDate=2024-11-04T13:07:20.412374, status=ORDER)

        // 주문 상세 내역 조회
        orderItemRepository.findAll().forEach(orderItem -> {
            System.out.println(orderItem);
            // 상품 상세 조회
            System.out.println(orderItem.getItem());
            // 주문 상세 내역 조회
            System.out.println(orderItem.getOrder());
            // 주문자 상세 조회
            System.out.println(orderItem.getOrder().getMember());
        });
    }

    @Test
    public void memberAndItemAndOrderRowTest() {

        OrderItem orderItem = orderItemRepository.findById(1L).get();

        // 주문 상세 내역 조회
        System.out.println(orderItem);
        // 상품 상세 조회
        System.out.println(orderItem.getItem());
        // 주문 상세 내역 조회
        System.out.println(orderItem.getOrder());
        // 주문자 상세 조회
        System.out.println(orderItem.getOrder().getMember());
    };

    // U
    @Test
    public void memberAndItemAndOrderUpdateTest() {
        // 주문자의 주소 변경
        // Member member = Member.builder()
        // .id(1L)
        // .name("user1")
        // .city("서울시")
        // .street("999-456")
        // .zipcode("123565")
        // .build();

        // Member member = memberRepository.findById(1L).get();
        // member.setStreet("9999-9999");

        // save : insert or update
        // 엔티티 매니저가 있어서 현재 entity 가 new 인지 기존 entity 인지 구분이 가능함
        // new : insert 호출 / 기존 entity : update 호출
        // update 는 무조건 전체 컬럼이 수정되는 형태로 작성됨
        // System.out.println(memberRepository.save(member));

        // 1번 주문 상품의 아이템(1번 아이템) 가격이 변경

        // 아이템 가격 변경
        Item item = itemRepository.findById(1L).get();
        item.setPrice(102000);
        itemRepository.save(item);

        OrderItem orderItem = orderItemRepository.findById(1L).get();
        orderItem.setPrice(102000);
        orderItemRepository.save(orderItem);
    }

    // D
    @Test
    public void memberAndItemAndOrderDeleteTest() {
        // 주문 취소

        // 주문 상품 취소
        orderItemRepository.deleteById(1L);

        // 주문 취소
        orderRepository.deleteById(1L);
    }

    // 양방향
    // Order ==> OrderItem 객체 그래프 탐색
    @Transactional
    @Test
    public void testOrderItemList() {
        Order order = orderRepository.findById(2L).get();
        System.out.println(order);

        // Order ==> OrderItem 탐색 시도
        order.getOrderItemList().forEach(orderItem -> System.out.println(orderItem));
    }

    @Transactional
    @Test
    public void testOrderList() {
        Member member = memberRepository.findById(1L).get();
        System.out.println(member);

        // Member ==> Order 탐색 시도
        member.getOrders().forEach(order -> System.out.println(order));
    }

    // 일대일
    @Test
    public void testDeliveryInsert() {

        // 배송 정보 입력
        Delivery delivery = Delivery.builder()
                .city("LA")
                .street("ROSEWILD")
                .zipcode("123-456")
                .deliveryStatus(DeliveryStatus.READY)
                .build();
        deliveryRepository.save(delivery);

        // order 와 배송정보 연결
        Order order = orderRepository.findById(3L).get();
        order.setDelivery(delivery);
        orderRepository.save(order);
    }

    @Test
    public void testOrderRead() {
        // order 조회(+ 배송정보)
        Order order = orderRepository.findById(3L).get();
        System.out.println(order);

        System.out.println(order.getDelivery());

    }

    // 양방향( 배송 => 주문)
    @Test
    public void testDeliveryRead() {
        // 배송정보 조회(+ order)
        Delivery delivery = deliveryRepository.findById(1L).get();
        System.out.println(delivery);

        System.out.println(delivery.getOrder());

    }

}
